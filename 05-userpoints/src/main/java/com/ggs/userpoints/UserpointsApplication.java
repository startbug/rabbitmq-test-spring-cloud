package com.ggs.userpoints;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ggs.userpoints.mapper")
public class UserpointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserpointsApplication.class, args);
    }

}
