package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.BrandEntity;
import com.cupdata.pms.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "品牌 管理")
@RestController
@RequestMapping("pms/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryBrandByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = brandService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<BrandEntity> queryBrandById(@PathVariable("id") Long id){
		BrandEntity brand = brandService.getById(id);

        return ResponseVo.ok(brand);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody BrandEntity brand){
		brandService.save(brand);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		brandService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
