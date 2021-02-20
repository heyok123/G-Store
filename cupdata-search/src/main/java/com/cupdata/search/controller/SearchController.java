package com.cupdata.search.controller;

import com.cupdata.common.bean.ResponseVo;
import com.cupdata.search.service.SearchService;
import com.cupdata.search.vo.SearchParamVo;
import com.cupdata.search.vo.SearchResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 搜索Controller
 * Created by Wsork on 2021/1/29 23:25.
 **/
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * @Description: 搜索
     * @Author: Wsork
     * @Date: 2021/1/29 23:44
     * @param: [searchParamVo]
     * @return: com.cupdata.common.bean.ResponseVo
     */
    @GetMapping
    public ResponseVo<SearchResponseVo> search(SearchParamVo searchParamVo){
        SearchResponseVo searchResponseVo = this.searchService.search(searchParamVo);
        return ResponseVo.ok(searchResponseVo);
    }





}
