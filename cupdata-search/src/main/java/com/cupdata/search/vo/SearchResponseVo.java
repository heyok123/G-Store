package com.cupdata.search.vo;

import com.cupdata.pms.entity.BrandEntity;
import com.cupdata.pms.entity.CategoryEntity;
import com.cupdata.search.pojo.Goods;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponseVo {

    // 封装品牌过滤条件
    private List<BrandEntity> brands;
    // 封装分类过滤条件
    private List<CategoryEntity> categories;
    // 封装规格参数过滤条件：[{attrId: 4, attrName: 运行内存, attrValues: [8G, 12G]}, {attrId: 5, attrName: 机身存储, attrValues: [128G, 256G]}]
    private List<SearchResponseAttrValueVo> filters;

    // 分页数据
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<Goods> goodsList;
}
