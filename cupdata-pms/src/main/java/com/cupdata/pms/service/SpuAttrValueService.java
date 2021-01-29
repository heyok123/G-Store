package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.SpuAttrValueEntity;

import java.util.List;

/**
 * spu属性值
 *
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
public interface SpuAttrValueService extends IService<SpuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    /**
     * @Description: 根据spuId查询检索属性及值
     * @Author: Wsork
     * @Date: 2021/1/29 11:07
     * @param: [spuId]
     * @return: java.util.List<com.cupdata.pms.entity.SpuAttrValueEntity>
     */
    List<SpuAttrValueEntity> querySpuAttrValueBySpuId(long spuId);

    /**
     * @Description: 根据spuId查询检索属性及值 √
     * @Author: Wsork
     * @Date: 2021/1/29 11:02
     * @param: []
     * @return: com.cupdata.common.bean.ResponseVo<java.util.List<com.cupdata.pms.entity.SpuAttrValueEntity>>
     */
    List<SpuAttrValueEntity> querySearchSpuAttrValuesByCidAndSpuId(Long cid, Long spuId);
}

