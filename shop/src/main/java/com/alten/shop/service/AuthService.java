package com.alten.shop.service;


import com.alten.shop.controller.dto.AuthResponseDTO;
import com.alten.shop.controller.dto.LoginDTO;
import com.alten.shop.controller.dto.RegisterDTO;

public interface AuthService {

    void register(RegisterDTO dto);

    AuthResponseDTO login(LoginDTO dto);
}