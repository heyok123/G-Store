package com.cupdata.search.upload;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.*;
import com.cupdata.search.feign.PmsInfoClient;
import com.cupdata.search.feign.WmsInfoClient;
import com.cupdata.search.pojo.Goods;
import com.cupdata.search.pojo.SearchAttrValueVo;
import com.cupdata.search.repository.GoodsRepository;
import com.cupdata.wms.entity.WareSkuEntity;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 数据导入
 * Created by Wsork on 2021/1/29 15:03.
 **/
public class ImportData {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private PmsInfoClient pmsInfoClient;
    @Autowired
    private WmsInfoClient wmsInfoClient;

    /**
     * @Description: 注: 在测试用例启动导入数据
     * @Author: Wsork
     * @Date: 2021/1/29 17:01
     * @param: []
     * @return: void
     */
    @Test
    public void testImportData() {
        // 创建索引
        this.elasticsearchRestTemplate.createIndex(Goods.class);
        // 创建映射
        this.elasticsearchRestTemplate.putMapping(Goods.class);
        // 分页信息
        int pageNum = 1;
        int pageSize = 10;
        do {
            //1.分页分批查询已上架SPU
            PageParamVo pageParamVo = new PageParamVo();
            pageParamVo.setPageNum(pageNum);
            pageParamVo.setPageSize(pageSize);
            ResponseVo<List<SpuEntity>> listResponseVo = pmsInfoClient.querySpuByPageJson(pageParamVo);
            List<SpuEntity> spuEntityList = listResponseVo.getData();
            if (CollectionUtils.isEmpty(spuEntityList)) {
                continue;
            }
            //2.查询spu下的sku 转换为goods对象
            spuEntityList.forEach(spuEntity -> {
                //2.1 获取sku
                ResponseVo<List<SkuEntity>> skuResponseVo = pmsInfoClient.querySkuBySpu(spuEntity.getId());
                List<SkuEntity> skuEntityList = skuResponseVo.getData();
                if (!CollectionUtils.isEmpty(skuEntityList)) {
                    //2.2 转换为Goods
                    List<Goods> goodsList = skuEntityList.stream().map(skuEntity -> {
                        Goods goods = new Goods();
                        //sku信息
                        goods.setSkuId(skuEntity.getId());
                        goods.setBrandId(skuEntity.getBrandId());
                        goods.setCategoryId(skuEntity.getCatagoryId());
                        goods.setTitle(skuEntity.getTitle());
                        goods.setSubTitle(skuEntity.getSubtitle());
                        //品牌信息
                        ResponseVo<BrandEntity> brandEntityResponseVo = pmsInfoClient.queryBrandById(skuEntity.getBrandId());
                        BrandEntity brandEntity = brandEntityResponseVo.getData();
                        if (brandEntity != null) {
                            goods.setBrandId(brandEntity.getId());
                            goods.setBrandName(brandEntity.getName());
                            goods.setLogo(brandEntity.getLogo());
                        }
                        //分类信息
                        ResponseVo<CategoryEntity> categoryEntityResponseVo = pmsInfoClient.queryCategoryById(skuEntity.getCatagoryId());
                        CategoryEntity categoryEntity = categoryEntityResponseVo.getData();
                        if (categoryEntity != null) {
                            goods.setCategoryId(categoryEntity.getId());
                            goods.setCategoryName(categoryEntity.getName());
                        }
                        //排序信息: 新品
                        goods.setCreateTime(spuEntity.getCreateTime());
                        //库存信息
                        ResponseVo<List<WareSkuEntity>> wareResponseVo = wmsInfoClient.queryWareSkuBySkuId(skuEntity.getId());
                        List<WareSkuEntity> wareSkuEntityList = wareResponseVo.getData();
                        if (!CollectionUtils.isEmpty(wareSkuEntityList)) {
                            goods.setStore(wareSkuEntityList.stream().anyMatch(
                                    wareSkuEntity -> wareSkuEntity.getStock() - wareSkuEntity.getStockLocked() > 0)
                            );
                        }
                        List<SearchAttrValueVo> attrValueVoList = new ArrayList<>();
                        //规格属性参数: SkuAttrValueEntity
                        ResponseVo<List<SkuAttrValueEntity>> skuAttrValueResponseVo = pmsInfoClient.queryAttrValueBySkuId(skuEntity.getId());
                        List<SkuAttrValueEntity> skuAttrValueEntities = skuAttrValueResponseVo.getData();
                        if (!CollectionUtils.isEmpty(skuAttrValueEntities)) {
                            List<SearchAttrValueVo> searchAttrValueVos = skuAttrValueEntities.stream().map(skuAttrValueEntity -> {
                                SearchAttrValueVo searchAttrValueVo = new SearchAttrValueVo();
                                BeanUtils.copyProperties(skuAttrValueEntity, searchAttrValueVo);
                                return searchAttrValueVo;
                            }).collect(Collectors.toList());
                            attrValueVoList.addAll(searchAttrValueVos);
                        }
                        //规格属性参数: SpuAttrValueEntity
                        ResponseVo<List<SpuAttrValueEntity>> spuAttrValueResponseVo = pmsInfoClient.querySpuAttrValueBySpuId(spuEntity.getId());
                        List<SpuAttrValueEntity> spuAttrValueEntities = spuAttrValueResponseVo.getData();
                        if (!CollectionUtils.isEmpty(spuAttrValueEntities)) {
                            List<SearchAttrValueVo> searchAttrValueVos = spuAttrValueEntities.stream().map(spuAttrValueEntity -> {
                                SearchAttrValueVo searchAttrValueVo = new SearchAttrValueVo();
                                BeanUtils.copyProperties(spuAttrValueEntity, searchAttrValueVo);
                                return searchAttrValueVo;
                            }).collect(Collectors.toList());
                            attrValueVoList.addAll(searchAttrValueVos);
                        }
                        goods.setSearchAttrs(attrValueVoList);
                        return goods;
                    }).collect(Collectors.toList());
                    //批量导入至es
                    goodsRepository.saveAll(goodsList);
                }
            });
            pageSize = spuEntityList.size();
            pageNum++;
        } while (pageSize == 100);

    }

}
