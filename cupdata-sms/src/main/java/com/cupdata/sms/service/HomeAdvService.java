package com.cupdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.sms.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

