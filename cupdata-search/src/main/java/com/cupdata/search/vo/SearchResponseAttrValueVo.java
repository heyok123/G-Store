package com.cupdata.search.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponseAttrValueVo {

    private Long attrId;

    private String attrName;

    private List<String> attrValues;
}
