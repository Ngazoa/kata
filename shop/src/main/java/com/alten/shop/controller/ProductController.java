package com.alten.shop.controller;

import com.alten.shop.model.User;
import com.alten.shop.service.ProductService;
import com.alten.shop.controller.dto.PageResponse;
import com.alten.shop.controller.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Liste paginée des produits")
    @GetMapping
    public PageResponse<ProductDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return productService.getAllProducts(page, size);
    }

    @Operation(summary = "Créer un produit (admin uniquement)")
    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO dto, HttpServletRequest request) {
        String email = ((User) request.getAttribute("user")).getEmail();
        return productService.createProduct(dto, email);
    }

    @Operation(summary = "Modifier un produit (admin uniquement)")
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id,
                             @RequestBody ProductDTO dto,
                             HttpServletRequest request) {

        String email = ((User) request.getAttribute("user")).getEmail();
        return productService.updateProduct(id, dto, email);
    }

    @Operation(summary = "Supprimer un produit (admin uniquement)")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletRequest request) {

        String email = ((User) request.getAttribute("user")).getEmail();
        productService.deleteProduct(id, email);
    }
}