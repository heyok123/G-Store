package com.cupdata.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.sms.mapper.HomeSubjectMapper;
import com.cupdata.sms.entity.HomeSubjectEntity;
import com.cupdata.sms.service.HomeSubjectService;


@Service("homeSubjectService")
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper, HomeSubjectEntity> implements HomeSubjectService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<HomeSubjectEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<HomeSubjectEntity>()
        );

        return new PageResultVo(page);
    }

}