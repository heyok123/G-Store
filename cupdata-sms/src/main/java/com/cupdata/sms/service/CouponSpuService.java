package com.cupdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.sms.entity.CouponSpuEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
public interface CouponSpuService extends IService<CouponSpuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

