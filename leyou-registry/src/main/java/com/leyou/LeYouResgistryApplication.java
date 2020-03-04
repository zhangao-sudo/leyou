package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Zhang Ao on 2020/1/14 19:38
 * Email:17863572518@163.com
 */
@SpringBootApplication
@EnableEurekaServer
public class LeYouResgistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeYouResgistryApplication.class,args);

    }

}
