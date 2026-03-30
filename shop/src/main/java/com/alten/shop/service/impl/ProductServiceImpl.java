package com.alten.shop.service.impl;


import com.alten.shop.model.Product;
import com.alten.shop.repository.ProductRepository;
import com.alten.shop.service.ProductService;
import com.alten.shop.controller.dto.PageResponse;
import com.alten.shop.controller.dto.ProductDTO;
import com.alten.shop.controller.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    private final ProductMapper productMapper;

    private void checkAdmin(String email){
        if(!email.equals("admin@admin.com")){
            throw new RuntimeException("Forbidden");
        }
    }

    @Override
    public PageResponse<ProductDTO> getAllProducts(int page, int size) {
        Page<Product> products = productRepo.findAll(PageRequest.of(page, size));

        PageResponse<ProductDTO> response = new PageResponse<>();
        response.setContent(products.map(productMapper::toDTO).getContent());
        response.setPage(products.getNumber());
        response.setSize(products.getSize());
        response.setTotalElements(products.getTotalElements());
        response.setTotalPages(products.getTotalPages());

        return response;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto, String email) {
        checkAdmin(email);
        Product product = productMapper.toEntity(dto);
        return productMapper.toDTO(productRepo.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto, String email) {
        checkAdmin(email);

        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateProductFromDto(dto, existing);

        return productMapper.toDTO(productRepo.save(existing));
    }

    @Override
    public void deleteProduct(Long id, String email) {
        checkAdmin(email);
        productRepo.deleteById(id);
    }
}