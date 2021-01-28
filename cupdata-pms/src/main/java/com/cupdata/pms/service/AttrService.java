package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.AttrEntity;

import java.util.List;

/**
 * 商品属性
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface AttrService extends IService<AttrEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 分类下的销售属性查询
     * @Author: Wsork
     * @Date: 2021/1/27 15:15
     * @param: [cid, type, searchType]
     * @return: java.util.List<com.cupdata.pms.entity.AttrEntity>
     */
    List<AttrEntity> queryAttrsByCid(long cid, Integer type, Integer searchType);
}

