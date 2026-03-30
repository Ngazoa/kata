package com.alten.shop.controller.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private String productName;
    private double productPrice;
    private int quantity;
}