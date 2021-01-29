package com.cupdata.search.service.impl;

import com.cupdata.search.service.SearchService;
import com.cupdata.search.vo.SearchParamVo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public void search(SearchParamVo searchParamVo) {
        assert buildDsl(searchParamVo) != null;
        SearchRequest searchRequest = new SearchRequest(new String[]{"goods"},buildDsl(searchParamVo));
        try {
            SearchResponse searchResponse = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @Description: DSL语句构建
     * Created by Wsork on 2021/1/29 23:52
     */
    private SearchSourceBuilder buildDsl(SearchParamVo searchParamVo) {
        return null;
    }
}
