package com.ggs.ordermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrdermanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanageApplication.class, args);
	}

}
