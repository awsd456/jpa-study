package com.green.jpaexam.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private  String name;
    private  int price;
    private  int stock;
    private Long cateId;
    private String description;
    private Long providerId;
}
