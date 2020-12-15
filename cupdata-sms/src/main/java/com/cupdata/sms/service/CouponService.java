package com.cupdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.sms.entity.CouponEntity;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
public interface CouponService extends IService<CouponEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

