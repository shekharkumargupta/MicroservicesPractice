package com.skcodify.orderservice.dto;

import com.skcodify.orderservice.Order;

public class OrderRequest {

    private String token;
    private Order order;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "token='" + token + '\'' +
                ", order=" + order +
                '}';
    }
}
