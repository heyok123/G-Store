package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.pms.entity.SpuDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author wsork
 * @email wsork@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SpuDescService extends IService<SpuDescEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

