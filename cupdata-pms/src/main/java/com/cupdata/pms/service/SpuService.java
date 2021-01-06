package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.SpuEntity;

/**
 * spu信息
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SpuService extends IService<SpuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: spu商品信息查询
     * @Author: Wsork
     * @Date: 2020/12/30 14:26
     * @param: [pageParamVo, categoryId]
     * @return: com.cupdata.common.bean.ResponseVo<com.cupdata.common.bean.PageResultVo>
     */
    PageResultVo querySpuInfo(PageParamVo pageParamVo, Long categoryId);
}

