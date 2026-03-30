package com.alten.shop.controller.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String firstname;
    private String email;
    private String password;
}