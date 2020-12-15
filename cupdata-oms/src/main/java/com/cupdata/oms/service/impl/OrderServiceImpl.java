package com.cupdata.oms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.oms.mapper.OrderMapper;
import com.cupdata.oms.entity.OrderEntity;
import com.cupdata.oms.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<OrderEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<OrderEntity>()
        );

        return new PageResultVo(page);
    }

}