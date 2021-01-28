package com.cupdata.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrEntity;
import com.cupdata.pms.entity.AttrGroupEntity;
import com.cupdata.pms.mapper.AttrGroupMapper;
import com.cupdata.pms.mapper.AttrMapper;
import com.cupdata.pms.service.AttrGroupService;
import com.cupdata.pms.service.AttrService;
import com.cupdata.pms.vo.GroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrService attrService;
    @Autowired
    private AttrMapper attrMapper;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<AttrGroupEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageResultVo(page);
    }

    // 根据三级分类id查询分组及组下的规格参数
    @Override
    public List<GroupVo> queryByCid(long catId) {
        // 查询属性分组
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("category_id",catId));
        // 查询分组下的规格参数
        List<GroupVo> groupVoList = attrGroupEntities.stream().map(attrGroupEntity -> {
            GroupVo groupVo = new GroupVo();
            BeanUtils.copyProperties(attrGroupEntity, groupVo);
            // 查询规格参数（通用属性查询）
            List<AttrEntity> attrEntityList = this.attrMapper.selectList(
                    new QueryWrapper<AttrEntity>().eq("group_id", attrGroupEntity.getId()).eq("type",1));
            groupVo.setAttrEntities(attrEntityList);
            return groupVo;
        }).collect(Collectors.toList());
        return groupVoList;
    }

}