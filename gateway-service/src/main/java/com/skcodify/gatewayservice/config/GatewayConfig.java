package com.skcodify.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://product-service"))
                        //.uri("http://localhost:8082"))
                .route("order-service", r -> r.path("/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-service"))
                //.uri("http://localhost:8081"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                        //.uri("http://localhost:8080"))
                .build();
    }
}
