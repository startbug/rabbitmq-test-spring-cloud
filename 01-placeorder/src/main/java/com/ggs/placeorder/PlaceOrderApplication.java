package com.ggs.placeorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ggs.placeorder.mapper")
@EnableFeignClients(basePackages = "com.ggs.placeorder.clients")
public class PlaceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceOrderApplication.class, args);
    }

}
