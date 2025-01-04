package com.skcodify.orderservice.controllers;

import com.skcodify.orderservice.Order;
import com.skcodify.orderservice.dto.OrderRequest;
import com.skcodify.orderservice.services.OrderService;
import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private Logger log = Logger.getLogger(OrderController.class.getName());

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/{id}/{productId}")
    public ResponseEntity<Order> addProduct(@PathVariable String id,
                                            @PathVariable Long productId,
                                            @RequestHeader("Authorization") String authorization){
        log.info("addProduct: " + authorization);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrder(new Order());
        orderRequest.getOrder().setId(id);
        orderRequest.setToken(authorization);
        Order order = orderService.addProduct(orderRequest, productId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.ok(orderService.create(order));
    }
}
