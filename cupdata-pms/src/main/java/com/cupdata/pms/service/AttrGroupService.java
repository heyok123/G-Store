package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrGroupEntity;

/**
 * 属性分组
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

