package com.cupdata.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.oms.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

