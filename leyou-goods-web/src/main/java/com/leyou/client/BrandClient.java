package com.leyou.client;

import com.item.leyou.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Zhang Ao on 2020/1/24 15:08
 * Email:17863572518@163.com
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
