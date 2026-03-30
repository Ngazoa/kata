package com.alten.shop.service.impl;


import com.alten.shop.controller.dto.CartItemDTO;
import com.alten.shop.controller.mapper.CartItemMapper;
import com.alten.shop.model.CartItem;
import com.alten.shop.model.Product;
import com.alten.shop.model.User;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.repository.UserRepository;
import com.alten.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserRepository userRepo;

    private final ProductRepository productRepo;

    private final CartItemMapper cartItemMapper;

    private User getUser(String email){
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<CartItemDTO> getCart(String email) {
        return getUser(email).getCart()
                .stream()
                .map(cartItemMapper::toDTO)
                .toList();
    }

    @Override
    public List<CartItemDTO> addToCart(Long productId, int quantity, String email) {

        User user = getUser(email);
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);

        user.getCart().add(item);
        userRepo.save(user);

        return getCart(email);
    }

    @Override
    public List<CartItemDTO> removeFromCart(Long productId, String email) {

        User user = getUser(email);

        user.getCart().removeIf(c -> c.getProduct().getId().equals(productId));
        userRepo.save(user);

        return getCart(email);
    }
}