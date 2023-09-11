package com.green.jpaexam.product.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductSelAllParam {

    private String productName;
    private int price;
}
