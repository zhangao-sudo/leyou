package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Zhang Ao on 2020/1/20 14:16
 * Email:17863572518@163.com
 */
@SpringBootApplication
@EnableDiscoveryClient
public class upload {
    public static void main(String[] args) {
        SpringApplication.run(upload.class);
    }
}
