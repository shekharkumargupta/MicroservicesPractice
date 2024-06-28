package com.skcodify.controllers;

import com.skcodify.domain.Product;
import com.skcodify.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final RestTemplate restTemplate;
    private final String EXT_PRODUCTS_URL = "https://dummyjson.com/products";

    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product1);
    }

    @GetMapping("/ext")
    public ResponseEntity<String> findExternalProducts(){
        return restTemplate.getForEntity(EXT_PRODUCTS_URL, String.class);
    }

    @GetMapping("/ext/{id}")
    public ResponseEntity<String> findByIdExternalProducts(@PathVariable String id){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(EXT_PRODUCTS_URL + "/" + id, String.class);
        String body = forEntity.getBody();
        return ResponseEntity.ok(body);
    }
}



