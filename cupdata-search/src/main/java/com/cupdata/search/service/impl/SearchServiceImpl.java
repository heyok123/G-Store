package com.cupdata.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.cupdata.pms.entity.BrandEntity;
import com.cupdata.pms.entity.CategoryEntity;
import com.cupdata.search.pojo.Goods;
import com.cupdata.search.service.SearchService;
import com.cupdata.search.vo.SearchParamVo;
import com.cupdata.search.vo.SearchResponseAttrValueVo;
import com.cupdata.search.vo.SearchResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 搜索service
 * Created by Wsork on 2021/1/29 23:27.
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @Description: 搜索
     * Created by Wsork on 2021/1/29 23:45
     */
    @Override
    public SearchResponseVo search(SearchParamVo searchParamVo) {
        assert buildDsl(searchParamVo) != null;
        //构建查询条件
        SearchRequest searchRequest = new SearchRequest(new String[]{"goods"},buildDsl(searchParamVo));
        try {
            //执行查询
            SearchResponse searchResponse = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //解析结果集
            SearchResponseVo searchResponseVo = this.parseSearchResult(searchResponse);
            searchResponseVo.setPageNum(searchParamVo.getPageNum());
            searchResponseVo.setPageSize(searchParamVo.getPageSize());
            return searchResponseVo;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        AtomicInteger atomicInteger = new AtomicInteger();
        return null;
    }


    /**
     * @Description: DSL语句构建
     * Created by Wsork on 2021/1/29 23:52
     */
    private SearchSourceBuilder buildDsl(SearchParamVo searchParamVo) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 检索条件：keyword=小米
        String keyword = searchParamVo.getKeyword();
        if (StringUtils.isEmpty(keyword)){
            // todo 可进行广告植入
            return null;
        }
        //1.检索条件查询(bool查询)
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //1.1 匹配查询
        boolQueryBuilder.must(QueryBuilders.matchQuery("title",keyword).operator(Operator.AND));
        //1.2 过滤
        //1.2.1 品牌过滤
        List<Long> brandId = searchParamVo.getBrandId();
        if (!CollectionUtils.isEmpty(brandId)){
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId",brandId));
        }
        //1.2.2 分类过滤
        Long cid = searchParamVo.getCid();
        if (cid != null){
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryId",cid));
        }
        //1.2.3 价格区间过滤
        Double priceFrom = searchParamVo.getPriceFrom();
        Double priceTo = searchParamVo.getPriceTo();
        if (priceFrom != null || priceTo != null){
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
            if (priceFrom != null){
                rangeQuery.gte(priceFrom);
            }
            if (priceTo != null){
                rangeQuery.lte(priceTo);
            }
            boolQueryBuilder.filter(rangeQuery);
        }
        //1.2.4 是否有货
        Boolean store = searchParamVo.getStore();
        if (store != null){
            boolQueryBuilder.filter(QueryBuilders.termQuery("store",store));
        }
        //1.2.5 规格参数过滤 props=5:高通-麒麟&props=6:骁龙865-硅谷1000
        List<String> props = searchParamVo.getProps();
        if (!CollectionUtils.isEmpty(props)){
            props.forEach(prop -> {
                String[] attrs = StringUtils.split(prop, ":");
                if (attrs != null && attrs.length == 2){
                    String attrId = attrs[0];
                    String attrValueStr = attrs[1];
                    String[] attrValues = StringUtils.split(attrValueStr, "-");
                    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                    boolQuery.must(QueryBuilders.termQuery("searchAttrs.attrId",attrId));
                    boolQuery.must(QueryBuilders.termQuery("searchAttrs.attrValue",attrValues));
                    boolQueryBuilder.filter(QueryBuilders.nestedQuery("searchAttrs",boolQuery, ScoreMode.None));
                }
            });
        }
        searchSourceBuilder.query(boolQueryBuilder);

        //2.构建排序
        Integer sort = searchParamVo.getSort();
        String field;
        SortOrder order;
        switch (sort){
            case 1: field = "price"; order = SortOrder.ASC; break;
            case 2: field = "price"; order = SortOrder.DESC; break;
            case 3: field = "createTime"; order = SortOrder.DESC; break;
            case 4: field = "sales"; order = SortOrder.DESC; break;
            default: field = "_score"; order = SortOrder.DESC; break;
        }
        searchSourceBuilder.sort(field,order);

        //3.构建分页
        Integer pageNum = searchParamVo.getPageNum();
        Integer pageSize = searchParamVo.getPageSize();
        searchSourceBuilder.from((pageNum - 1) * pageSize);
        searchSourceBuilder.size(pageSize);

        //4.构建高亮
        searchSourceBuilder.highlighter(new HighlightBuilder()
                .field("title")
                .preTags("<font style='color:red'>")
                .postTags("</font>"));

        //5.构建聚合
        // 5.1 品牌聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("brandIdAgg").field("brandId")
                        .subAggregation(AggregationBuilders.terms("brandNameAgg").field("brandName"))
                        .subAggregation(AggregationBuilders.terms("logoAgg").field("logo"))
        );
        //5.2 分类聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("categoryIdAgg").field("categoryId")
                        .subAggregation(AggregationBuilders.terms("categoryNameAgg").field("categoryName"))
        );
        //5.3 规格参数的嵌套聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.nested("attrAgg","searchAttrs")
                        .subAggregation(AggregationBuilders.terms("attrIdAgg").field("searchAttrs.attrId")
                                .subAggregation(AggregationBuilders.terms("attrNameAgg").field("searchAttrs.attrName"))
                                .subAggregation(AggregationBuilders.terms("attrValueAgg").field("searchAttrs.attrValue"))
                        ));

        //6.构建结果集过滤
        searchSourceBuilder.fetchSource(new String[]{"skuId","title","price","defaultImage"},null);
        System.out.println(searchSourceBuilder.toString());
        return searchSourceBuilder;
    }

    /**
     * @Description: 解析搜索结果集
     * Created by Wsork on 2021/2/20 15:12
     */
    private SearchResponseVo parseSearchResult(SearchResponse searchResponse) {
        SearchResponseVo searchResponseVo = new SearchResponseVo();
        SearchHits hits = searchResponse.getHits();
        //命中总记录数
        searchResponseVo.setTotal(hits.getTotalHits());

        SearchHit[] hitsHits = hits.getHits();
        List<Goods> goodsList = Stream.of(hitsHits).map(hitsHit -> {
            //获取内层hits的_source 数据
            String goodsJson = hitsHit.getSourceAsString();
            //反序列化为goods对象
            Goods goods = JSON.parseObject(goodsJson, Goods.class);
            //获取高亮的title覆盖掉普通title
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            HighlightField highLightTitleStr = highlightFields.get("title");
            String highLightTitle = highLightTitleStr.getFragments()[0].toString();
            goods.setTitle(highLightTitle);
            return goods;
        }).collect(Collectors.toList());
        searchResponseVo.setGoodsList(goodsList);

        //聚合结果集的解析
        Map<String, Aggregation> aggregationMap = searchResponse.getAggregations().asMap();
        // 1. 解析聚合结果集，获取品牌》
        // {attrId: null, attrName: "品牌"， attrValues: [{id: 1, name: 硅谷, logo: http://www.guigu.com/logo.gif}, {}]}
        ParsedLongTerms brandIdAgg = (ParsedLongTerms)aggregationMap.get("brandIdAgg");
        List<? extends Terms.Bucket> buckets = brandIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(buckets)){
            List<BrandEntity> brands = buckets.stream().map(bucket -> { // {id: 1, name: 硅谷, logo: http://www.guigu.com/logo.gif}
                // 为了得到指定格式的json字符串，创建了一个map
                BrandEntity brandEntity = new BrandEntity();
                // 获取brandIdAgg中的key，这个key就是品牌的id
                Long brandId = ((Terms.Bucket) bucket).getKeyAsNumber().longValue();
                brandEntity.setId(brandId);
                // 解析品牌名称的子聚合，获取品牌名称
                Map<String, Aggregation> brandAggregationMap = ((Terms.Bucket) bucket).getAggregations().asMap();
                ParsedStringTerms brandNameAgg = (ParsedStringTerms)brandAggregationMap.get("brandNameAgg");
                brandEntity.setName(brandNameAgg.getBuckets().get(0).getKeyAsString());
                // 解析品牌logo的子聚合，获取品牌 的logo
                ParsedStringTerms logoAgg = (ParsedStringTerms)brandAggregationMap.get("logoAgg");
                List<? extends Terms.Bucket> logoAggBuckets = logoAgg.getBuckets();
                if (!CollectionUtils.isEmpty(logoAggBuckets)){
                    brandEntity.setLogo(logoAggBuckets.get(0).getKeyAsString());
                }
                // 把map反序列化为json字符串
                return brandEntity;
            }).collect(Collectors.toList());
            searchResponseVo.setBrands(brands);
        }

        // 2. 解析聚合结果集，获取分类
        ParsedLongTerms categoryIdAgg = (ParsedLongTerms)aggregationMap.get("categoryIdAgg");
        List<? extends Terms.Bucket> categoryIdAggBuckets = categoryIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(categoryIdAggBuckets)){
            List<CategoryEntity> categories = categoryIdAggBuckets.stream().map(bucket -> { // {id: 225, name: 手机}
                CategoryEntity categoryEntity = new CategoryEntity();
                // 获取bucket的key，key就是分类的id
                Long categoryId = ((Terms.Bucket) bucket).getKeyAsNumber().longValue();
                categoryEntity.setId(categoryId);
                // 解析分类名称的子聚合，获取分类名称
                ParsedStringTerms categoryNameAgg = (ParsedStringTerms)((Terms.Bucket) bucket).getAggregations().get("categoryNameAgg");
                categoryEntity.setName(categoryNameAgg.getBuckets().get(0).getKeyAsString());
                return categoryEntity;
            }).collect(Collectors.toList());
            searchResponseVo.setCategories(categories);
        }

        // 3. 解析聚合结果集，获取规格参数
        ParsedNested attrAgg = (ParsedNested)aggregationMap.get("attrAgg");
        ParsedLongTerms attrIdAgg = (ParsedLongTerms)attrAgg.getAggregations().get("attrIdAgg");
        List<? extends Terms.Bucket> attrIdAggBuckets = attrIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(attrIdAggBuckets)) {
            List<SearchResponseAttrValueVo> filters = attrIdAggBuckets.stream().map(bucket -> {
                SearchResponseAttrValueVo responseAttrVo = new SearchResponseAttrValueVo();
                // 规格参数id
                responseAttrVo.setAttrId(((Terms.Bucket) bucket).getKeyAsNumber().longValue());
                // 规格参数的名称
                ParsedStringTerms attrNameAgg = (ParsedStringTerms)((Terms.Bucket) bucket).getAggregations().get("attrNameAgg");
                responseAttrVo.setAttrName(attrNameAgg.getBuckets().get(0).getKeyAsString());
                // 规格参数值
                ParsedStringTerms attrValueAgg = (ParsedStringTerms)((Terms.Bucket) bucket).getAggregations().get("attrValueAgg");
                List<? extends Terms.Bucket> attrValueAggBuckets = attrValueAgg.getBuckets();
                if (!CollectionUtils.isEmpty(attrValueAggBuckets)){
                    List<String> attrValues = attrValueAggBuckets.stream().map(Terms.Bucket::getKeyAsString).collect(Collectors.toList());
                    responseAttrVo.setAttrValues(attrValues);
                }
                return responseAttrVo;
            }).collect(Collectors.toList());
            searchResponseVo.setFilters(filters);
        }

        return searchResponseVo;
    }

}
