package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Zhang Ao on 2020/1/14 22:07
 * Email:17863572518@163.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LeYouGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeYouGatewayApplication.class,args);
    }
}
