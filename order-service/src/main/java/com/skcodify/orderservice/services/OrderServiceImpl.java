package com.skcodify.orderservice.services;

import com.github.javafaker.Faker;
import com.skcodify.orderservice.Order;
import com.skcodify.orderservice.config.AppProperties;
import com.skcodify.orderservice.domain.Product;
import com.skcodify.orderservice.dto.OrderRequest;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final Map<String, Order> orders = new HashMap<>();
    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    @Override
    public List<Order> findAll() {
        log.info("Orders >> findAll");
        return orders.values().stream().toList();
    }

    @Override
    public Order findById(String id) {
        return orders.get(id);
    }

    @Override
    public Order create(Order order) {
        Faker faker = new Faker();
        order.setId(faker.idNumber().valid());
        return orders.put(order.getId(), order);
    }

    @Override
    public Order remove(String id) {
        return orders.remove(id);
    }

    @Override
    public Order addProduct(OrderRequest orderRequest, Long productId) {
        log.info("gatewayServiceUrl: " + appProperties.getGatewayUrl());
        log.info("productServiceUrl: " + appProperties.getProductUrl());
        String url = appProperties.getGatewayUrl()
                + "/" + appProperties.getProductUrl()
                + "/" + productId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", orderRequest.getToken());

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Product> entity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Product.class);
        log.info("Product: " + entity.getBody());
        orders.get(orderRequest.getOrder().getId()).getProducts().add(entity.getBody());
        return orders.get(orderRequest.getOrder().getId());
    }
}
