package com.cupdata.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.ums.entity.UserEntity;

import java.util.Map;

/**
 * 用户表
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:32:01
 */
public interface UserService extends IService<UserEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

