package com.skcodify.orderservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "service")

@Data
public class AppProperties {
    private String gatewayUrl;
    private String productUrl;
    
}