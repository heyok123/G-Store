package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrEntity;
import com.cupdata.pms.entity.SkuAttrValueEntity;
import com.cupdata.pms.mapper.AttrMapper;
import com.cupdata.pms.mapper.SkuAttrValueMapper;
import com.cupdata.pms.service.SkuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("skuAttrValueService")
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValueEntity> implements SkuAttrValueService {


    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private AttrMapper attrMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuAttrValueEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuAttrValueEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * @Description: 根据spuId查询检索属性及值 2 ×
     * Created by Wsork on 2021/1/29 11:24
     */
    @Override
    public List<SkuAttrValueEntity> querySearchAttrValueBySkuId(Long skuId) {
        return skuAttrValueMapper.querySearchAttrValueBySkuId(skuId);
    }

    /**
     * @Description: 根据skuId查询检索属性及值 1 √
     * Created by Wsork on 2021/1/29 17:30
     */
    @Override
    public List<SkuAttrValueEntity> querySearchSkuAttrValuesByCidAndSkuId(Long cid, Long skuId) {
        // 根据分类id查询出检索类型的规格参数
        List<AttrEntity> attrEntities = attrMapper.selectList(new QueryWrapper<AttrEntity>()
                        .eq("category_id", cid)
                        .eq("search_type", 1)
        );
        if (CollectionUtils.isEmpty(attrEntities)){
            return null;
        }
        // 获取检索规格参数id
        List<Long> attrIds = attrEntities.stream().map(AttrEntity::getId).collect(Collectors.toList());
        // 根据skuId和attrIds查询销售检索类型的规格参数和值
        return this.list(new QueryWrapper<SkuAttrValueEntity>()
                .eq("sku_id", skuId)
                .in("attr_id", attrIds)
        );
    }

}