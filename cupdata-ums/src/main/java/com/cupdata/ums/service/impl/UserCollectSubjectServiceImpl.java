package com.cupdata.ums.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.ums.mapper.UserCollectSubjectMapper;
import com.cupdata.ums.entity.UserCollectSubjectEntity;
import com.cupdata.ums.service.UserCollectSubjectService;


@Service("userCollectSubjectService")
public class UserCollectSubjectServiceImpl extends ServiceImpl<UserCollectSubjectMapper, UserCollectSubjectEntity> implements UserCollectSubjectService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<UserCollectSubjectEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<UserCollectSubjectEntity>()
        );

        return new PageResultVo(page);
    }

}