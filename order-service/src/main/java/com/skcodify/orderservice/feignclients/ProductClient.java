package com.skcodify.orderservice.feignclients;


import com.skcodify.orderservice.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "products", url = "http://ms-product-service:8082/")
public interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<Product> getProducts();

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    public Product getProduct(@PathVariable Long id);
}
