package com.green.jpaexam.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public class ProductRes {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private String description;
    private String cateNm;
    private String providerNm;
    private LocalDateTime createdAt;


    public ProductRes(Long number, String name, int price, int stock, String description, String cateNm, String providerNm, LocalDateTime createdAt) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.cateNm = cateNm;
        this.providerNm = providerNm;
        this.createdAt = createdAt;
    }
}
