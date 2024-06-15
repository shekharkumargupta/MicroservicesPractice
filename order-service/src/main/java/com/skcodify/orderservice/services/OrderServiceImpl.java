package com.skcodify.orderservice.services;

import com.github.javafaker.Faker;
import com.skcodify.orderservice.Order;
import com.skcodify.orderservice.domain.Product;
import com.skcodify.orderservice.feignclients.ProductClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<String, Order> orders;
    private final ProductClient productClient;

    public OrderServiceImpl(ProductClient productClient){
        this.productClient = productClient;
        orders = new HashMap<>();
        Faker faker = new Faker();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.setId(faker.idNumber().valid());
            order.setUserName(faker.name().username());
            order.setProducts(new ArrayList<Product>());
            orders.put(order.getId(), order);
            addProduct(order.getId(), (long) faker.number().numberBetween(1, 30));
        }
    }

    @Override
    public List<Order> findAll() {
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
        Product product = productClient.getProduct(productId);
        orders.get(id).getProducts().add(product);
        return orders.get(id);
    }
}
