package com.cata.alten.shop.controller;

import com.cata.alten.shop.model.Product;
import com.cata.alten.shop.model.User;
import com.cata.alten.shop.repository.ProductRepository;
import com.cata.alten.shop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    private boolean isAdmin(User user){
        return user.getEmail().equals("admin@admin.com");
    }

    @GetMapping
    public List<Product> getAll() { return productRepo.findAll(); }

    @PostMapping
    public Product addProduct(@RequestBody Product p, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        if(!isAdmin(user)) throw new RuntimeException("Forbidden");
        return productRepo.save(p);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product p, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        if(!isAdmin(user)) throw new RuntimeException("Forbidden");
        Product prod = productRepo.findById(id).orElseThrow();
        p.setId(id);
        return productRepo.save(p);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        if(!isAdmin(user)) throw new RuntimeException("Forbidden");
        productRepo.deleteById(id);
        return "Deleted";
    }
}