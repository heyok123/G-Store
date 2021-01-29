package com.cupdata.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cupdata.pms.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
	
}
