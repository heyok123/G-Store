package com.cupdata.pms.feign;

import com.cupdata.sms.api.SmsSkuSaleApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 新增sku的营销信息(feign--sms)
 * Created by Wsork on 2021/1/28 10:44.
 **/
@FeignClient("cupdata-sms-service")
public interface SmsSkuSaleClient extends SmsSkuSaleApi {
}
