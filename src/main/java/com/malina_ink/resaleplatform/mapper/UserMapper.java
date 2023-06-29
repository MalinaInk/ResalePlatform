package com.malina_ink.resaleplatform.mapper;

import com.malina_ink.resaleplatform.dto.UserDto;
import com.malina_ink.resaleplatform.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setImage(user.getImage());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirstname(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setImage(dto.getImage());
        return user;
    }

}
