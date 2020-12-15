package com.cupdata.oms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.oms.mapper.OrderReturnApplyMapper;
import com.cupdata.oms.entity.OrderReturnApplyEntity;
import com.cupdata.oms.service.OrderReturnApplyService;


@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<OrderReturnApplyEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<OrderReturnApplyEntity>()
        );

        return new PageResultVo(page);
    }

}