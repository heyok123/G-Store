package com.cupdata.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.wms.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:42:29
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

