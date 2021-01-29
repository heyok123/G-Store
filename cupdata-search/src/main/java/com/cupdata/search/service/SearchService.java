package com.cupdata.search.service;

import com.cupdata.search.vo.SearchParamVo;

/**
 * @Description: TODO
 * Created by Wsork on 2021/1/29 23:27.
 **/
public interface SearchService {

    /**
     * @Description: 搜索
     * @Author: Wsork
     * @Date: 2021/1/29 23:44
     * @param: [searchParamVo]
     * @return: void
     */
    void search(SearchParamVo searchParamVo);
}
