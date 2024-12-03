package com.skcodify.services;

import com.skcodify.domain.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductService {

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product findById(Long id);
    public CompletableFuture<List<Product>> findAll();
    public List<Product> findByCategory(String category);
}
