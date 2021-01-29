package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.SpuAttrValueEntity;
import com.cupdata.pms.service.SpuAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * spu属性值
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "spu属性值 管理")
@RestController
@RequestMapping("pms/spuattrvalue")
public class SpuAttrValueController {

    @Autowired
    private SpuAttrValueService spuAttrValueService;

    /**
     * @Description: 根据spuId查询检索属性及值 √
     * @Author: Wsork
     * @Date: 2021/1/29 11:02
     * @param: []
     * @return: com.cupdata.common.bean.ResponseVo<java.util.List<com.cupdata.pms.entity.SpuAttrValueEntity>>
     */
    @GetMapping("search/{cid}/{spuId}")
    public ResponseVo<List<SpuAttrValueEntity>> querySearchSpuAttrValuesByCidAndSpuId(
            @PathVariable("cid")Long cid, @PathVariable("spuId")Long spuId
    ){
        List<SpuAttrValueEntity> spuAttrValueEntities = this.spuAttrValueService.querySearchSpuAttrValuesByCidAndSpuId(cid, spuId);
        return ResponseVo.ok(spuAttrValueEntities);
    }

    /**
     * @Description: 根据spuId查询检索属性及值 ×
     * @Author: Wsork
     * @Date: 2021/1/29 11:02
     * @param: []
     * @return: com.cupdata.common.bean.ResponseVo<java.util.List<com.cupdata.pms.entity.SpuAttrValueEntity>>
     */
    @GetMapping("/{spuId}")
    @ApiOperation("根据spuId查询检索属性及值")
    public ResponseVo<List<SpuAttrValueEntity>> querySpuAttrValueBySpuId(@PathVariable("spuId") long spuId){
        List<SpuAttrValueEntity> spuAttrValueEntities = this.spuAttrValueService.querySpuAttrValueBySpuId(spuId);
        return ResponseVo.ok(spuAttrValueEntities);
    }

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> querySpuAttrValueByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = spuAttrValueService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<SpuAttrValueEntity> querySpuAttrValueById(@PathVariable("id") Long id){
		SpuAttrValueEntity spuAttrValue = spuAttrValueService.getById(id);

        return ResponseVo.ok(spuAttrValue);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody SpuAttrValueEntity spuAttrValue){
		spuAttrValueService.save(spuAttrValue);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody SpuAttrValueEntity spuAttrValue){
		spuAttrValueService.updateById(spuAttrValue);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		spuAttrValueService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
