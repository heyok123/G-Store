package com.cupdata.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cupdata.pms.entity.SpuAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * spu属性值
 * 
 * @author 这周日没空
 * @email lypbenlf@163.com
 * @date 2020-12-15 13:44:06
 */
@Mapper
public interface SpuAttrValueMapper extends BaseMapper<SpuAttrValueEntity> {

    /**
     * @Description: 根据spuId查询检索属性及值
     * Created by Wsork on 2021/1/29 11:10
     */
    List<SpuAttrValueEntity> querySpuAttrValueBySpuId(long spuId);}
