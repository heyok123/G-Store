package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.CategoryBrandEntity;
import com.cupdata.pms.service.CategoryBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "品牌分类关联 管理")
@RestController
@RequestMapping("pms/categorybrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryCategoryBrandByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = categoryBrandService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<CategoryBrandEntity> queryCategoryBrandById(@PathVariable("id") Long id){
		CategoryBrandEntity categoryBrand = categoryBrandService.getById(id);

        return ResponseVo.ok(categoryBrand);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.save(categoryBrand);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.updateById(categoryBrand);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		categoryBrandService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
