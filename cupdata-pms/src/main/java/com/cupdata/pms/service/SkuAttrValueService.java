package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.pms.entity.SkuAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author wsork
 * @email wsork@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SkuAttrValueService extends IService<SkuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

