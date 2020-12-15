package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.pms.entity.CommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author wsork
 * @email wsork@163.com
 * @date 2020-12-15 13:44:06
 */
public interface CommentService extends IService<CommentEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

