package com.cupdata.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.sms.entity.SkuBoundsEntity;
import com.cupdata.sms.vo.SkuSaleVo;

/**
 * 商品spu积分设置
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
public interface SkuBoundsService extends IService<SkuBoundsEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 新增sku的营销信息
     * @Author: Wsork
     * @Date: 2021/1/28 9:50
     * @param: [skuSaleVo]
     * @return: void
     */
    void saveSkuSale(SkuSaleVo skuSaleVo);
}

