package com.cupdata.pms.controller;

import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.pms.entity.CommentReplayEntity;
import com.cupdata.pms.service.CommentReplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品评价回复关系
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Api(tags = "商品评价回复关系 管理")
@RestController
@RequestMapping("pms/commentreplay")
public class CommentReplayController {

    @Autowired
    private CommentReplayService commentReplayService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryCommentReplayByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = commentReplayService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<CommentReplayEntity> queryCommentReplayById(@PathVariable("id") Long id){
		CommentReplayEntity commentReplay = commentReplayService.getById(id);

        return ResponseVo.ok(commentReplay);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody CommentReplayEntity commentReplay){
		commentReplayService.save(commentReplay);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody CommentReplayEntity commentReplay){
		commentReplayService.updateById(commentReplay);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		commentReplayService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
