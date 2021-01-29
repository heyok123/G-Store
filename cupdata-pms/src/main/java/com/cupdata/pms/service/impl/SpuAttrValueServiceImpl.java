package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrEntity;
import com.cupdata.pms.entity.SpuAttrValueEntity;
import com.cupdata.pms.mapper.AttrMapper;
import com.cupdata.pms.mapper.SpuAttrValueMapper;
import com.cupdata.pms.service.SpuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service("spuAttrValueService")
public class SpuAttrValueServiceImpl extends ServiceImpl<SpuAttrValueMapper, SpuAttrValueEntity> implements SpuAttrValueService {

    @Autowired
    private SpuAttrValueMapper spuAttrValueMapper;
    @Autowired
    private AttrMapper attrMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuAttrValueEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuAttrValueEntity>()
        );

        return new PageResultVo(page);
    }

    /**
     * @Description: 根据spuId查询检索属性及值 ×
     * Created by Wsork on 2021/1/29 11:07
     */
    @Override
    public List<SpuAttrValueEntity> querySpuAttrValueBySpuId(long spuId) {
        return spuAttrValueMapper.querySpuAttrValueBySpuId(spuId);
    }

    /**
     * @Description: 根据spuId查询检索属性及值 √
     * Created by Wsork on 2021/1/29 11:07
     */
    @Override
    public List<SpuAttrValueEntity> querySearchSpuAttrValuesByCidAndSpuId(Long cid, Long spuId) {
        // 查询分类下的检索类型的规格参数
        List<AttrEntity> attrEntities = this.attrMapper.selectList(new QueryWrapper<AttrEntity>()
                .eq("category_id", cid)
                .eq("search_type", 1));
        if (CollectionUtils.isEmpty(attrEntities)){
            return null;
        }
        List<Long> attrIds = attrEntities.stream().map(AttrEntity::getId).collect(Collectors.toList());
        return this.list(new QueryWrapper<SpuAttrValueEntity>()
                .eq("spu_id", spuId)
                .in("attr_id", attrIds));
    }

}