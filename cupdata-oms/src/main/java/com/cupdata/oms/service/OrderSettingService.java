package com.cupdata.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.oms.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

