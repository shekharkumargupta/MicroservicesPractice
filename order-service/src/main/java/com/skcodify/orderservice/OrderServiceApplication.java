package com.skcodify.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.skcodify.orderservice.config.AppProperties;

@SpringBootApplication
//@EnableFeignClients(basePackages = {"com.skcodify.orderservice.feignclients", "com.skcodify.orderservice.services"})
@EnableDiscoveryClient
@EnableConfigurationProperties(AppProperties.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
