package com.alten.shop.controller.mapper;


import com.alten.shop.controller.dto.CartItemDTO;
import com.alten.shop.model.CartItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "productPrice")
    CartItemDTO toDTO(CartItem cartItem);
}