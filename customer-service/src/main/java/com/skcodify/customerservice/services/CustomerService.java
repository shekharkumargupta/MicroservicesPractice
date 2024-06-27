package com.skcodify.customerservice.services;

import com.skcodify.customerservice.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> create(Customer customer);
    Optional<Customer> findById(String customerId);
    List<Customer> findAll();

}
