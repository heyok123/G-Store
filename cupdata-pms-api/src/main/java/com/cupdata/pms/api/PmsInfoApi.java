package com.cupdata.pms.api;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: pms接口工程
 * Created by Wsork on 2021/1/29 14:35.
 **/
public interface PmsInfoApi {

    /**
     * @Description: 分页查询已上架SPU
     * Created by Wsork on 2021/1/29 14:37
     */
    @PostMapping("/pms/spu/spuJson")
    ResponseVo<List<SpuEntity>> querySpuByPageJson(@RequestBody PageParamVo pageParamVo);

    /**
     * @Description: 根据spuId查询sku列表
     * Created by Wsork on 2021/1/29 14:38
     */
    @GetMapping("/pms/sku/sku/{spuId}")
    ResponseVo<List<SkuEntity>> querySkuBySpu(@PathVariable("spuId") Long spuId);

    /**
     * @Description: 分类详情查询
     * Created by Wsork on 2021/1/29 14:39
     */
    @GetMapping("/pms/category/{id}")
    ResponseVo<CategoryEntity> queryCategoryById(@PathVariable("id") Long id);

    /**
     * @Description: 品牌详情查询
     * Created by Wsork on 2021/1/29 14:41
     */
    @GetMapping("/pms/brand/{id}")
    @ApiOperation("详情查询")
    ResponseVo<BrandEntity> queryBrandById(@PathVariable("id") Long id);

    /**
     * @Description: 根据skuId查询检索属性及值 √
     * Created by Wsork on 2021/1/29 14:42
     */
    @GetMapping("/pms/skuattrvalue/search/{cid}/{skuId}")
    ResponseVo<List<SkuAttrValueEntity>> querySearchSkuAttrValuesByCidAndSkuId(
            @PathVariable("cid")Long cid, @PathVariable("skuId")Long skuId
    );

    /**
     * @Description: 根据spuId查询检索属性及值 √
     * Created by Wsork on 2021/1/29 14:43
     */
    @GetMapping("pms/spuattrvalue/search/{cid}/{spuId}")
    ResponseVo<List<SpuAttrValueEntity>> querySearchSpuAttrValuesByCidAndSpuId(
            @PathVariable("cid")Long cid, @PathVariable("spuId")Long spuId
    );

    /**
     * @Description: 根据skuId查询检索属性及值 ×
     * Created by Wsork on 2021/1/29 14:42
     */
    @GetMapping("/pms/skuattrvalue/{skuId}")
    ResponseVo<List<SkuAttrValueEntity>> queryAttrValueBySkuId(@PathVariable("skuId")Long skuId);

    /**
     * @Description: 根据spuId查询检索属性及值 ×
     * Created by Wsork on 2021/1/29 14:43
     */
    @GetMapping("/pms/spuattrvalue/{spuId}")
    ResponseVo<List<SpuAttrValueEntity>> querySpuAttrValueBySpuId(@PathVariable long spuId);

}
