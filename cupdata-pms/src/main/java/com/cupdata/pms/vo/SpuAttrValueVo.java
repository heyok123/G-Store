package com.cupdata.pms.vo;

import com.cupdata.pms.entity.SpuAttrValueEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: 基本属性扩展类
 * Created by Wsork on 2021/1/27 15:40.
 **/
public class SpuAttrValueVo extends SpuAttrValueEntity {

    // 字段转换：前端穿的字段valueSelected，实体字段attrValue
    public void setValueSelected(List<Object> valueSelected){
        if (CollectionUtils.isEmpty(valueSelected)) {
            return;
        }
        this.setAttrValue(StringUtils.join(valueSelected,","));
    }


}
