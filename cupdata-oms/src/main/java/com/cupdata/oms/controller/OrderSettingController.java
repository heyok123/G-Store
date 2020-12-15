package com.cupdata.oms.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cupdata.oms.entity.OrderSettingEntity;
import com.cupdata.oms.service.OrderSettingService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.common.bean.PageParamVo;

/**
 * 订单配置信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
@Api(tags = "订单配置信息 管理")
@RestController
@RequestMapping("oms/ordersetting")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryOrderSettingByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = orderSettingService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<OrderSettingEntity> queryOrderSettingById(@PathVariable("id") Long id){
		OrderSettingEntity orderSetting = orderSettingService.getById(id);

        return ResponseVo.ok(orderSetting);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.save(orderSetting);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.updateById(orderSetting);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		orderSettingService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
