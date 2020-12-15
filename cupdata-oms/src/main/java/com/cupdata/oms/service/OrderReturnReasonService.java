package com.cupdata.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.oms.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

