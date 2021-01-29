package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.SkuAttrValueEntity;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SkuAttrValueService extends IService<SkuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 根据spuId查询检索属性及值 2 ×
     * @Author: Wsork
     * @Date: 2021/1/29 11:23
     * @param: [skuId]
     * @return: java.util.List<com.cupdata.pms.entity.SkuAttrValueEntity>
     */
    List<SkuAttrValueEntity> querySearchAttrValueBySkuId(Long skuId);

    /**
     * @Description: 根据skuId查询检索属性及值 1 √
     * @Author: Wsork
     * @Date: 2021/1/29 17:30
     * @param: [cid, skuId]
     * @return: java.util.List<com.cupdata.pms.entity.SkuAttrValueEntity>
     */
    List<SkuAttrValueEntity> querySearchSkuAttrValuesByCidAndSkuId(Long cid, Long skuId);
}

