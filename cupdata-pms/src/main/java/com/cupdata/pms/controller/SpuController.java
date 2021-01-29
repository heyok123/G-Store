package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.SpuEntity;
import com.cupdata.pms.service.SpuService;
import com.cupdata.pms.vo.SpuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "spu信息 管理")
@RestController
@RequestMapping("pms/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * @Description: 分页查询已上架SPU
     * @Author: Wsork
     * @Date: 2021/1/29 10:59
     * @param: [pageParamVo]
     * @return: com.cupdata.common.bean.ResponseVo<java.util.List<com.cupdata.pms.entity.SpuEntity>>
     */
    @PostMapping("/spuJson")
    @ApiOperation("分页查询已上架SPU")
    public ResponseVo<List<SpuEntity>> querySpuByPageJson(@RequestBody PageParamVo pageParamVo){
        PageResultVo pageResultVo = this.spuService.queryPage(pageParamVo);
        List<SpuEntity> spuEntities = (List<SpuEntity>) (pageResultVo.getList());
        List<SpuEntity> spuEntityList = spuEntities.stream()
                .filter(spuEntity -> spuEntity.getPublishStatus().equals(1))
                .collect(Collectors.toList());
        return ResponseVo.ok(spuEntityList);
//        return ResponseVo.ok((List<SpuEntity>)(pageResultVo.getList()));

    }

    /**
     * @Description: SPU大保存
     * @Author: Wsork
     * @Date: 2021/1/27 16:08
     * @param: [spuVo]
     * @return: com.cupdata.common.bean.ResponseVo
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo bigSave(@RequestBody SpuVo spuVo){
        this.spuService.bigSave(spuVo);
        return ResponseVo.ok();
    }

    /**
     * @Description: spu商品信息查询
     * @Author: Wsork
     * @Date: 2020/12/30 14:26
     * @param: [pageParamVo, categoryId]
     * @return: com.cupdata.common.bean.ResponseVo<com.cupdata.common.bean.PageResultVo>
     */
    @ApiOperation("spu商品信息查询")
    @GetMapping("category/{categoryId}")
    public ResponseVo<PageResultVo> querySpuInfo(PageParamVo pageParamVo, @PathVariable("categoryId")Long categoryId){
        PageResultVo pageResultVo = this.spuService.querySpuInfo(pageParamVo, categoryId);
        return ResponseVo.ok(pageResultVo);
    }

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> querySpuByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = spuService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<SpuEntity> querySpuById(@PathVariable("id") Long id){
		SpuEntity spu = spuService.getById(id);

        return ResponseVo.ok(spu);
    }

    /**
     * 保存
     */
//    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody SpuEntity spu){
		spuService.save(spu);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody SpuEntity spu){
		spuService.updateById(spu);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		spuService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
