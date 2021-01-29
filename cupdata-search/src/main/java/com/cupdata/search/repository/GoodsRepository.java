package com.cupdata.search.repository;

import com.cupdata.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description: GoodsRepository: 商品数据搜索
 * Created by Wsork on 2021/1/29 14:52.
 **/
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
