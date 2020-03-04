package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Zhang Ao on 2020/1/29 15:55
 * Email:17863572518@163.com
 */
@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
