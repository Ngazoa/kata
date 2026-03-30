package com.alten.shop.controller;

import com.alten.shop.service.AuthService;
import com.alten.shop.controller.dto.AuthResponseDTO;
import com.alten.shop.controller.dto.LoginDTO;
import com.alten.shop.controller.dto.RegisterDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Créer un compte")
    @PostMapping("/account")
    public void register(@RequestBody RegisterDTO dto) {
        authService.register(dto);
    }

    @Operation(summary = "Connexion utilisateur")
    @PostMapping("/token")
    public AuthResponseDTO login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }
}