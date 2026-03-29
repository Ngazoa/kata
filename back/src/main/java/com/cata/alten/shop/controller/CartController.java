package com.cata.alten.shop.controller;

import com.cata.alten.shop.model.CartItem;
import com.cata.alten.shop.model.Product;
import com.cata.alten.shop.model.User;
import com.cata.alten.shop.repository.ProductRepository;
import com.cata.alten.shop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/{productId}")
    public List<CartItem> addToCart(@PathVariable Long productId, @RequestBody QuantityRequest req, HttpServletRequest http){
        User user = (User) http.getAttribute("user");
        Product p = productRepo.findById(productId).orElseThrow();
        CartItem item = new CartItem();
        item.setProduct(p);
        item.setQuantity(req.getQuantity());
        user.getCart().add(item);
        userRepo.save(user);
        return user.getCart();
    }

    @DeleteMapping("/{productId}")
    public List<CartItem> removeFromCart(@PathVariable Long productId, HttpServletRequest http){
        User user = (User) http.getAttribute("user");
        user.getCart().removeIf(c->c.getProduct().getId().equals(productId));
        userRepo.save(user);
        return user.getCart();
    }

    @GetMapping
    public List<CartItem> getCart(HttpServletRequest http){
        User user = (User) http.getAttribute("user");
        return user.getCart();
    }

    @Data
    static class QuantityRequest { private int quantity; }
}