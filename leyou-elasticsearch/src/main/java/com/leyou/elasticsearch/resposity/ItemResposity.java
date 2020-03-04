package com.leyou.elasticsearch.resposity;

import com.leyou.elasticsearch.poji.Goods;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Zhang Ao on 2020/1/23 19:41
 * Email:17863572518@163.com
 */
public interface ItemResposity extends ElasticsearchRepository<Goods,Long> {
}
