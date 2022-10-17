package com.kodilla.ecommercee.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Long> cartList;
    private Long groupId;
}
