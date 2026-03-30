package com.alten.shop.controller.mapper;

import com.alten.shop.controller.dto.UserDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface UserMapper {

    UserDTO toDTO(main.java.com.cata.alten.shop.model.User user);
}