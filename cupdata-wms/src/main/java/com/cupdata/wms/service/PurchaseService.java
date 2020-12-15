package com.cupdata.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.wms.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:42:29
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

