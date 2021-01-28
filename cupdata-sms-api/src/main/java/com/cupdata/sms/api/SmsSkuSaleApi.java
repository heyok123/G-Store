package com.cupdata.sms.api;

import com.cupdata.common.bean.ResponseVo;
import com.cupdata.sms.vo.SkuSaleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 营销接口
 * Created by Wsork on 2021/1/28 10:36.
 **/
public interface SmsSkuSaleApi {

    /**
     * @Description: 新增sku的营销信息
     * @Author: Wsork
     * @Date: 2021/1/28 10:40
     * @param: [skuSaleVo]
     * @return: com.cupdata.common.bean.ResponseVo
     */
    @PostMapping("/sms/skubounds/skuSale/save")
    public ResponseVo saveSkuSale(@RequestBody SkuSaleVo skuSaleVo);

}
