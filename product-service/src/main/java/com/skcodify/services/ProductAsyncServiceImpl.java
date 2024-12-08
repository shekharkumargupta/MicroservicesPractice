package com.skcodify.services;

import com.google.gson.Gson;
import com.skcodify.domain.Product;
import com.skcodify.domain.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Service
@Qualifier("productAsyncService")
public class ProductAsyncServiceImpl implements ProductService{

    private static final Logger log = LoggerFactory.getLogger(ProductAsyncServiceImpl.class);
    private final RestTemplate restTemplate;
    private final String EXT_PRODUCTS_URL = "https://dummyjson.com/products";

    public ProductAsyncServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public CompletableFuture<List<Product>> findAll(){
        CompletableFuture<String> completableFuture = fetchProductResponse();
        return completableFuture.thenComposeAsync(this::transformToProductList);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return List.of();
    }

    private CompletableFuture<String> fetchProductResponse(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("Thread name: " + Thread.currentThread().getName());
            log.info("Fetching response string data from PRODUCT API");
            ResponseEntity<String> forEntity = restTemplate.getForEntity(EXT_PRODUCTS_URL, String.class);
            String body = forEntity.getBody();
            log.info("Response string populated from Product API!");
            return body;
        });
    }

    private CompletableFuture<List<Product>> transformToProductList(String productResponseString){
        return CompletableFuture.supplyAsync(() -> {
            log.info("Transforming response string to Product List...");
            delay(3000);
            Gson gson = new Gson();
            List<Product> products = gson.fromJson(productResponseString, ProductResponse.class).getProducts();
            log.info("Product List prepared from response string!");
            return products;
        });
    }

    private void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
