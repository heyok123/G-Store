package com.cupdata.pms.vo;

import com.cupdata.pms.entity.AttrEntity;
import com.cupdata.pms.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @Description: GroupVo:分类下分组及规格参数
 * Created by Wsork on 2021/1/27 14:38.
 **/
@Data
public class GroupVo extends AttrGroupEntity {

    private List<AttrEntity> attrEntities;

}
