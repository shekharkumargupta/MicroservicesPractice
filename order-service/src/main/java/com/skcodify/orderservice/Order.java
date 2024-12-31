package com.skcodify.orderservice;

import com.skcodify.orderservice.domain.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private String id;
    private String userName;
    private List<Product> products = new ArrayList<>();


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", products=" + products +
                '}';
    }
}
