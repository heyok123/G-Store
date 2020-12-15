package com.cupdata.oms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.oms.mapper.OrderSettingMapper;
import com.cupdata.oms.entity.OrderSettingEntity;
import com.cupdata.oms.service.OrderSettingService;


@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<OrderSettingEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<OrderSettingEntity>()
        );

        return new PageResultVo(page);
    }

}