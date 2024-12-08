package com.skcodify.services;

import com.github.javafaker.Faker;
import com.skcodify.domain.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Qualifier("productService")
public class ProductServiceImpl implements ProductService{

    private final Map<Long, Product> products;

    public ProductServiceImpl(){
        products = new HashMap<>();
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Product product = new Product();
            product.setId((long)i + 1);
            product.setTitle(faker.book().title());
            product.setCategory(faker.book().genre());
            product.setPrice(
                    (float) faker.number().randomDouble(2, 30, 300));
            products.put(product.getId(), product);
        }
    }


    @Override
    public Product createProduct(Product product) {
        return products.computeIfAbsent(product.getId(), k -> product);
    }

    @Override
    public Product updateProduct(Product product) {
        return products.computeIfPresent(product.getId(), (k, v) -> product);
    }

    @Override
    public Product findById(Long id) {
        return products.values()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(new Product());
    }

    @Override
    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            return products.values().stream().toList();
        });
    }

    @Override
    public List<Product> findByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }
}
