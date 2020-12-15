package com.cupdata.pms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;

import com.cupdata.pms.mapper.CommentMapper;
import com.cupdata.pms.entity.CommentEntity;
import com.cupdata.pms.service.CommentService;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<CommentEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<CommentEntity>()
        );

        return new PageResultVo(page);
    }

}