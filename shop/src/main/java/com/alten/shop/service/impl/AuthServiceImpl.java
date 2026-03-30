package com.alten.shop.service.impl;

import com.alten.shop.model.User;
import com.alten.shop.repository.UserRepository;
import com.alten.shop.security.JwtUtil;
import com.alten.shop.service.AuthService;
import com.alten.shop.controller.dto.AuthResponseDTO;
import com.alten.shop.controller.dto.LoginDTO;
import com.alten.shop.controller.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;

    private  final JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void register(RegisterDTO dto) {

        if(userRepo.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstname(dto.getFirstname());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepo.save(user);
    }

    @Override
    public AuthResponseDTO login(LoginDTO dto) {

        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!encoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token);
    }
}