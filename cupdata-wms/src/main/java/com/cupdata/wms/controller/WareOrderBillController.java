package com.cupdata.wms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.wms.entity.WareOrderBillEntity;
import com.cupdata.wms.service.WareOrderBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存工作单
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:42:29
 */
@Api(tags = "库存工作单 管理")
@RestController
@RequestMapping("wms/wareorderbill")
public class WareOrderBillController {

    @Autowired
    private WareOrderBillService wareOrderBillService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryWareOrderBillByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = wareOrderBillService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<WareOrderBillEntity> queryWareOrderBillById(@PathVariable("id") Long id){
		WareOrderBillEntity wareOrderBill = wareOrderBillService.getById(id);

        return ResponseVo.ok(wareOrderBill);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody WareOrderBillEntity wareOrderBill){
		wareOrderBillService.save(wareOrderBill);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody WareOrderBillEntity wareOrderBill){
		wareOrderBillService.updateById(wareOrderBill);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		wareOrderBillService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
