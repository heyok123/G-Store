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

import com.cupdata.oms.entity.OrderReturnApplyEntity;
import com.cupdata.oms.service.OrderReturnApplyService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.common.bean.PageParamVo;

/**
 * 订单退货申请
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:37:26
 */
@Api(tags = "订单退货申请 管理")
@RestController
@RequestMapping("oms/orderreturnapply")
public class OrderReturnApplyController {

    @Autowired
    private OrderReturnApplyService orderReturnApplyService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryOrderReturnApplyByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = orderReturnApplyService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<OrderReturnApplyEntity> queryOrderReturnApplyById(@PathVariable("id") Long id){
		OrderReturnApplyEntity orderReturnApply = orderReturnApplyService.getById(id);

        return ResponseVo.ok(orderReturnApply);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody OrderReturnApplyEntity orderReturnApply){
		orderReturnApplyService.save(orderReturnApply);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody OrderReturnApplyEntity orderReturnApply){
		orderReturnApplyService.updateById(orderReturnApply);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		orderReturnApplyService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
