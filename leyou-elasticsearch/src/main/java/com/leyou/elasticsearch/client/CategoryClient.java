package com.leyou.elasticsearch.client;

import com.item.leyou.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Zhang Ao on 2020/1/24 15:11
 * Email:17863572518@163.com
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
