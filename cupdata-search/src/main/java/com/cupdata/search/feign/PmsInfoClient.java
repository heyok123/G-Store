package com.cupdata.search.feign;

import com.cupdata.pms.api.PmsInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 商品接口
 * Created by Wsork on 2021/1/29 14:47.
 **/
@FeignClient("cupdata-pms-service")
public interface PmsInfoClient extends PmsInfoApi {
}
