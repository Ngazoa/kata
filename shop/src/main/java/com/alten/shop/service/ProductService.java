package com.alten.shop.service;


import com.alten.shop.controller.dto.PageResponse;
import com.alten.shop.controller.dto.ProductDTO;

public interface ProductService {

    PageResponse<ProductDTO> getAllProducts(int page, int size);

    ProductDTO createProduct(ProductDTO dto, String userEmail);

    ProductDTO updateProduct(Long id, ProductDTO dto, String userEmail);

    void deleteProduct(Long id, String userEmail);
}