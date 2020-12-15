package com.cupdata.ums.mapper;

import com.cupdata.ums.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:32:01
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
