package com.cupdata.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.wms.entity.WareOrderBillDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:42:29
 */
public interface WareOrderBillDetailService extends IService<WareOrderBillDetailEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

