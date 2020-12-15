package com.cupdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.sms.entity.SkuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

