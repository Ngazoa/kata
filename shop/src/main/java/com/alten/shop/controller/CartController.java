package com.alten.shop.controller;

import com.alten.shop.model.User;
import com.alten.shop.service.CartService;
import com.alten.shop.controller.dto.CartItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private String getEmail(HttpServletRequest request){
        return ((User)request.getAttribute("user")).getEmail();
    }

    @Operation(summary = "Récupérer le panier")
    @GetMapping
    public List<CartItemDTO> getCart(HttpServletRequest request){
        return cartService.getCart(getEmail(request));
    }

    @Operation(summary = "Ajouter au panier")
    @PostMapping("/{productId}")
    public List<CartItemDTO> addToCart(@PathVariable Long productId,
                                       @RequestParam(defaultValue = "1") int quantity,
                                       HttpServletRequest request){
        return cartService.addToCart(productId, quantity, getEmail(request));
    }

    @Operation(summary = "Supprimer du panier")
    @DeleteMapping("/{productId}")
    public List<CartItemDTO> remove(@PathVariable Long productId,
                                    HttpServletRequest request){
        return cartService.removeFromCart(productId, getEmail(request));
    }
}
