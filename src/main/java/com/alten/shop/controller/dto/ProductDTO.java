package com.alten.shop.controller.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String inventoryStatus;
    private double rating;
}