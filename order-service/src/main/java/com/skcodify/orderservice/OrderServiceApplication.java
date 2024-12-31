package com.skcodify.orderservice;

import com.skcodify.orderservice.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableFeignClients(basePackages = {"com.skcodify.orderservice.feignclients", "com.skcodify.orderservice.services"})
//@EnableDiscoveryClient
@EnableConfigurationProperties(AppProperties.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
