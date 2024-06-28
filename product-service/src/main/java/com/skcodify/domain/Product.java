package com.skcodify.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String author;
    private String price;
    private String category;
}
