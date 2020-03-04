package com.leyou.cart.Client;

import com.item.leyou.api.SpecficationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Zhang Ao on 2020/1/24 15:10
 * Email:17863572518@163.com
 */
@FeignClient(value = "item-service")
public interface SpectionClient extends SpecficationApi {
}
