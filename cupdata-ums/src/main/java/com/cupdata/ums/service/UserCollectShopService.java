package com.cupdata.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.ums.entity.UserCollectShopEntity;

import java.util.Map;

/**
 * 关注店铺表
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:32:01
 */
public interface UserCollectShopService extends IService<UserCollectShopEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

