package com.cata.alten.shop.security;


import com.cata.alten.shop.model.User;
import com.cata.alten.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if(jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                User user = userRepo.findByEmail(email).orElse(null);
                request.setAttribute("user", user);
            }
        }
        filterChain.doFilter(request,response);
    }
}