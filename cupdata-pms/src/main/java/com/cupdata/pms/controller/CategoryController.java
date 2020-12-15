package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.CategoryEntity;
import com.cupdata.pms.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author wsork
 * @email wsork@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "商品三级分类 管理")
@RestController
@RequestMapping("pms/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryCategoryByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = categoryService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<CategoryEntity> queryCategoryById(@PathVariable("id") Long id){
		CategoryEntity category = categoryService.getById(id);

        return ResponseVo.ok(category);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		categoryService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
