package com.skcodify.customerservice.services;

import com.github.javafaker.Faker;
import com.skcodify.customerservice.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final Map<String, Customer> customerMap = new HashMap<>();

    public CustomerServiceImpl(){
        Faker faker = new Faker();
        for (int i = 1; i < 10; i++){
            Customer customer = new Customer(
                    faker.idNumber().ssnValid(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            customerMap.put(customer.getId(), customer);
        }
    }

    @Override
    public Optional<Customer> create(Customer customer) {
        Faker faker = new Faker();
        customer.setId(faker.idNumber().ssnValid());
        return Optional.ofNullable(customerMap.put(customer.getId(), customer));
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return Optional.of(customerMap.get(customerId));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}
