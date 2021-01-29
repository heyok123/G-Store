package com.cupdata.wms.api;

import com.cupdata.common.bean.ResponseVo;
import com.cupdata.wms.entity.WareSkuEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description: 库存接口
 * Created by Wsork on 2021/1/29 14:45.
 **/
public interface WmsInfoApi {

    /**
     * @Description: 根据skuId查询库存
     * Created by Wsork on 2021/1/29 14:45
     */
    @GetMapping("/wms/waresku/sku/{skuId}")
    ResponseVo<List<WareSkuEntity>> queryWareSkuBySkuId(@PathVariable("skuId") Long skuId);
}
