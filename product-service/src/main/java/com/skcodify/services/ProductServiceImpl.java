package com.skcodify.services;

import com.github.javafaker.Faker;
import com.skcodify.domain.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    private final Map<Long, Product> products;

    public ProductServiceImpl(){
        products = new HashMap<>();
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Product product = new Product();
            product.setId((long)i + 1);
            product.setName(faker.book().title());
            product.setAuthor(faker.book().author());
            product.setCategory(faker.book().genre());
            product.setPrice(String.valueOf(
                    faker.number().randomDouble(2, 30, 300))
            );
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
        return products.getOrDefault(id, new Product());
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }
}
