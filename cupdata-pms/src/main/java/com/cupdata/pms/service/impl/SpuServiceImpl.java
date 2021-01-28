package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.*;
import com.cupdata.pms.feign.SmsSkuSaleClient;
import com.cupdata.pms.mapper.SpuMapper;
import com.cupdata.pms.service.*;
import com.cupdata.pms.vo.SkuVo;
import com.cupdata.pms.vo.SpuAttrValueVo;
import com.cupdata.pms.vo.SpuVo;
import com.cupdata.sms.vo.SkuSaleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Autowired
    private SpuDescService spuDescService;
    @Autowired
    private SpuAttrValueService spuAttrValueService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private SmsSkuSaleClient smsSkuSaleClient;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );
        return new PageResultVo(page);
    }

    /**
     * @Description: spu商品信息查询
     * @Author: Wsork
     * @Date: 2020/12/30 14:26
     */
    @Override
    public PageResultVo querySpuInfo(PageParamVo pageParamVo, Long categoryId) {
        // 封装查询条件
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        // 如果分类id不为0，要根据分类id查，否则查全部
        if (categoryId != 0){
            wrapper.eq("category_id", categoryId);
        }
        // 如果用户输入了检索条件，根据检索条件查
        String key = pageParamVo.getKey();
        if (StringUtils.isNotBlank(key)){
            wrapper.and(t -> t.like("name", key).or().like("id", key));
        }
        return new PageResultVo(this.page(pageParamVo.getPage(), wrapper));
    }

    // SPU大保存
    @Override
    public void bigSave(SpuVo spuVo) {
        // 1.保存SPU
        // 1.1 spu
        spuVo.setPublishStatus(1);
        spuVo.setCreateTime(new Date());
        spuVo.setUpdateTime(spuVo.getCreateTime());
        this.save(spuVo);
        Long spuId = spuVo.getId(); // >> 获取spuId <<
        // 1.2 spu_desc
        SpuDescEntity descEntity = new SpuDescEntity();
        descEntity.setSpuId(spuId); // 配置中主键为auto,entity需手动输入设置主键
        descEntity.setDecript(StringUtils.join(spuVo.getSpuImages(),","));
        spuDescService.save(descEntity);
        // 1.3 spu_attr_value
        List<SpuAttrValueVo> baseAttrs = spuVo.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)) {
            List<SpuAttrValueEntity> spuAttrValueVoList = baseAttrs.stream().map(spuAttrValueVo -> {
                spuAttrValueVo.setSpuId(spuId);
                spuAttrValueVo.setSort(1);
                return spuAttrValueVo;
            }).collect(Collectors.toList());
            spuAttrValueService.saveBatch(spuAttrValueVoList);
        }

        // 2.保存SKU
        // 2.1 sku
        List<SkuVo> skuVos = spuVo.getSkuVos();
        if (CollectionUtils.isEmpty(skuVos)) {
            return;
        }
        skuVos.forEach(skuVo -> {
            SkuEntity skuEntity = new SkuEntity();
            BeanUtils.copyProperties(skuVo,skuEntity);
            skuEntity.setSpuId(spuId);
            skuEntity.setBrandId(skuVo.getBrandId());
            skuEntity.setCatagoryId(skuVo.getCatagoryId());
            List<String> images = skuVo.getImages();
            if (!CollectionUtils.isEmpty(images)) {
                skuEntity.setDefaultImage(skuEntity.getDefaultImage() == null ? images.get(0) : skuEntity.getDefaultImage());
            }
            skuService.save(skuEntity);
            Long skuId = skuEntity.getId(); // >> 获取skuId <<

            // 2.2 sku_images
            if (!CollectionUtils.isEmpty(images)) {
                String defaultImage = images.get(0);
                List<SkuImagesEntity> imagesEntityList = images.stream().map(image -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    // defaultImage:默认图[0 - 不是默认图，1 - 是默认图]
                    skuImagesEntity.setDefaultStatus(StringUtils.equals(defaultImage, image) ? 1 : 0);
                    skuImagesEntity.setUrl(image);
                    skuImagesEntity.setSort(0);
                    skuImagesEntity.setSkuId(skuId);
                    return skuImagesEntity;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(imagesEntityList);
            }

            // 2.3 sku_attr_value
            List<SkuAttrValueEntity> saleAttrs = skuVo.getSaleAttrs();
            if (!CollectionUtils.isEmpty(saleAttrs)) {
                saleAttrs.forEach(saleAttr -> {
                    saleAttr.setSort(0);
                    saleAttr.setSkuId(skuId);
                    AttrEntity attrEntity = attrService.getById(saleAttr.getAttrId());
                    if (attrEntity != null) {
                        saleAttr.setAttrName(attrEntity.getName());
                    }
                });
                skuAttrValueService.saveBatch(saleAttrs);
            }

            // 3.保存SMS：sku_bounds、sku_ladder、sku_full_reduction
            SkuSaleVo skuSaleVo = new SkuSaleVo();
            BeanUtils.copyProperties(skuVo,skuSaleVo);
            skuSaleVo.setSkuId(skuId);
            smsSkuSaleClient.saveSkuSale(skuSaleVo);
        });



    }

}