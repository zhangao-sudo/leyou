package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by Zhang Ao on 2020/1/15 15:32
 * Email:17863572518@163.com
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.mapper")
public class LeYouItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeYouItemApplication.class);
    }
}
