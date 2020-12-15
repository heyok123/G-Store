package com.cupdata.sms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.sms.entity.CouponEntity;
import com.cupdata.sms.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:39:56
 */
@Api(tags = "优惠券信息 管理")
@RestController
@RequestMapping("sms/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryCouponByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = couponService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<CouponEntity> queryCouponById(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return ResponseVo.ok(coupon);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		couponService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
