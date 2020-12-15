package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.pms.entity.SpuAttrValueEntity;

import java.util.Map;

/**
 * spu属性值
 *
 * @author wsork
 * @email wsork@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SpuAttrValueService extends IService<SpuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

