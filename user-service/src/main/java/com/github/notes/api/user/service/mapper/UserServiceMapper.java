package com.github.notes.api.user.service.mapper;

import com.github.notes.api.common.config.dto.user.request.RegisterDto;
import com.github.notes.api.common.config.dto.user.response.UserDto;
import com.github.notes.api.user.service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserServiceMapper {

    User toEntity(RegisterDto dto);

    UserDto toDto(User entity);
}
