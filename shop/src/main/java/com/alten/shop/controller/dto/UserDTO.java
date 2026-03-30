package com.alten.shop.controller.dto;


import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstname;
    private String email;
    private List<CartItemDTO> cart;
}