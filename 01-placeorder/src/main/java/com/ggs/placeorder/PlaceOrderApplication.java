package com.ggs.placeorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class PlaceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceOrderApplication.class, args);
    }

}
