package com.cupdata.search.feign;

import com.cupdata.wms.api.WmsInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 库存接口
 * Created by Wsork on 2021/1/29 14:48.
 **/
@FeignClient("cupdata-wms-service")
public interface WmsInfoClient extends WmsInfoApi {
}
