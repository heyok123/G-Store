package com.cupdata.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.sms.mapper.HomeSubjectSpuMapper;
import com.cupdata.sms.entity.HomeSubjectSpuEntity;
import com.cupdata.sms.service.HomeSubjectSpuService;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageResultVo(page);
    }

}