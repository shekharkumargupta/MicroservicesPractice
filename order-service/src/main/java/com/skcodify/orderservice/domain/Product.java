package com.skcodify.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;
    private String title;
    private String category;
    private int stock;
    private float price;
    private float discountPercentage;
    private float rating;
    private String brand;
    private int weight;
    private String availabilityStatus;

}
