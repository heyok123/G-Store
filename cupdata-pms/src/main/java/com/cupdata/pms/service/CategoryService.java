package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.CategoryEntity;

/**
 * 商品三级分类
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

