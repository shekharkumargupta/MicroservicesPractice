package com.skcodify.orderservice.services;

import com.github.javafaker.Faker;
import com.skcodify.orderservice.Order;
import com.skcodify.orderservice.config.AppProperties;
import com.skcodify.orderservice.domain.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Order addProduct(String id, Long productId) {
        log.info("gatewayServiceUrl: " + appProperties.getGatewayUrl());
        log.info("productServiceUrl: " + appProperties.getProductUrl());
        String url = appProperties.getGatewayUrl()
                + "/" + appProperties.getProductUrl()
                + "/" + productId;
        ResponseEntity<Product> entity = restTemplate.getForEntity(url, Product.class);
        log.info("Product: " + entity.getBody());
        orders.get(id).getProducts().add(entity.getBody());
        return orders.get(id);
    }
}
