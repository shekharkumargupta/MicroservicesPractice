package com.skcodify.orderservice.services;

import com.skcodify.orderservice.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();
    Order findById(String id);

    Order create(Order order);

    Order remove(String id);

    Order addProduct(String id, Long productId);
}
