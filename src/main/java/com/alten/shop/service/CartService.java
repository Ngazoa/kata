package com.alten.shop.service;


import com.alten.shop.controller.dto.CartItemDTO;

import java.util.List;

public interface CartService {

    List<CartItemDTO> getCart(String email);

    List<CartItemDTO> addToCart(Long productId, int quantity, String email);

    List<CartItemDTO> removeFromCart(Long productId, String email);
}