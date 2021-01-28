package com.cupdata.pms.vo;

import com.cupdata.pms.entity.SpuEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description: spu扩展对象
 * Created by Wsork on 2021/1/27 15:32.
 **/
@Data
public class SpuVo extends SpuEntity {

    // 图片信息
    private List<String> spuImages;

    // 基本信息
    private List<SpuAttrValueVo> baseAttrs;

    // sku信息
    private List<SkuVo> skuVos;

}
