package com.cupdata.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cupdata.pms.entity.SkuAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku销售属性&值
 * 
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Mapper
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValueEntity> {

    /**
     * @Description: 根据spuId查询检索属性及值
     * Created by Wsork on 2021/1/29 11:25
     */
    List<SkuAttrValueEntity> querySearchAttrValueBySkuId(Long skuId);
}
