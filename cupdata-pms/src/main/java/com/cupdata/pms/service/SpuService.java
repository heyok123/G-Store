package com.cupdata.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cupdata.common.bean.PageParamVo;
import com.cupdata.common.bean.PageResultVo;
import com.cupdata.pms.entity.SpuEntity;
import com.cupdata.pms.vo.SpuVo;

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

    /**
     * @Description: SPU大保存
     * @Author: Wsork
     * @Date: 2021/1/27 16:09
     * @param: [spuVo]
     * @return: void
     */
    void bigSave(SpuVo spuVo);

    /**
     * @Description: 保存SKU
     * Created by Wsork on 2021/1/28 11:28
     */
    void saveSkuAndSaleInfo(SpuVo spuVo, Long spuId);

    /**
     * @Description: 保存spu基本属性信息
     * Created by Wsork on 2021/1/28 11:25
     */
    void saveSpuAttrValue(SpuVo spuVo, Long spuId);

    /**
     * @Description: 保存spu描述信息（图片）
     * Created by Wsork on 2021/1/28 11:24
     */
    void saveSpuDesc(SpuVo spuVo, Long spuId);

    /**
     * @Description: 保存spu基本信息
     * Created by Wsork on 2021/1/28 11:24
     */
    Long saveSpu(SpuVo spuVo);
}

