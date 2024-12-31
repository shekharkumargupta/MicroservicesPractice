package com.skcodify.controllers;

import com.skcodify.domain.Product;
import com.skcodify.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Qualifier("productAsyncService")
    @Autowired
    private ProductService productService;


//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Product>>> findAll(){
        CompletableFuture<List<Product>> completableFuture = productService.findAll();
        return completableFuture.thenApplyAsync(ResponseEntity::ok)
                .exceptionally(throwable -> {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
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
}



