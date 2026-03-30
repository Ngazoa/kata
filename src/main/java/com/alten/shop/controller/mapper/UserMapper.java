package com.alten.shop.controller.mapper;

import com.alten.shop.controller.dto.UserDTO;
import com.alten.shop.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface UserMapper {

    UserDTO toDTO(User user);
}