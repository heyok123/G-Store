package com.cupdata.wms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.wms.entity.WareEntity;
import com.cupdata.wms.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 16:42:29
 */
@Api(tags = "仓库信息 管理")
@RestController
@RequestMapping("wms/ware")
public class WareController {

    @Autowired
    private WareService wareService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryWareByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = wareService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<WareEntity> queryWareById(@PathVariable("id") Long id){
		WareEntity ware = wareService.getById(id);

        return ResponseVo.ok(ware);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody WareEntity ware){
		wareService.save(ware);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody WareEntity ware){
		wareService.updateById(ware);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		wareService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
