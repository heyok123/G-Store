package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrEntity;
import com.cupdata.pms.mapper.AttrMapper;
import com.cupdata.pms.service.AttrService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity> implements AttrService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<AttrEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<AttrEntity>()
        );

        return new PageResultVo(page);
    }

    // 分类下的销售属性查询
    @Override
    public List<AttrEntity> queryAttrsByCid(long cid, Integer type, Integer searchType) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        // cid为0 查询所有分类
        if (cid != 0) {
            wrapper.eq("category_id",cid);
        }
        // type:属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
        if (type != null) {
            wrapper.eq("type",type);
        }
        // searchType:是否需要检索[0-不需要，1-需要]
        if (searchType != null) {
            wrapper.eq("search_type",searchType);
        }
        return this.list(wrapper);
    }

}