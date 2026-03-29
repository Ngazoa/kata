package com.cata.alten.shop.controller;

import com.cata.alten.shop.model.User;
import com.cata.alten.shop.repository.UserRepository;
import com.cata.alten.shop.security.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/account")
    public String createAccount(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User created";
    }

    @PostMapping("/token")
    public TokenResponse login(@RequestBody LoginRequest req){
        User user = userRepo.findByEmail(req.getEmail()).orElseThrow();
        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenResponse(token);
    }

    @Data
    static class LoginRequest { private String email; private String password; }
    @Data
    static class TokenResponse { private String token; public TokenResponse(String token){this.token=token;} }
}