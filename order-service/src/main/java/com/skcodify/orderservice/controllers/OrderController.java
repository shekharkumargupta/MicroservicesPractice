package com.skcodify.orderservice.controllers;

import com.skcodify.orderservice.Order;
import com.skcodify.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/ping")
    public String ping(){
        return "Version 0.0.1";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/{id}/{productId}")
    public ResponseEntity<Order> addProduct(@PathVariable String id,
                                            @PathVariable Long productId){
        Order order = orderService.addProduct(id, productId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.ok(orderService.create(order));
    }
}
