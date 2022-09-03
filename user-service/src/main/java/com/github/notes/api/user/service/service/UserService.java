package com.github.notes.api.user.service.service;

import com.github.notes.api.common.config.dto.user.request.LoginDto;
import com.github.notes.api.common.config.dto.user.request.RegisterDto;
import com.github.notes.api.common.config.dto.user.response.UserDto;

public interface UserService {

    String register(RegisterDto dto);

    String login(LoginDto dto);

    UserDto getUser(String email);
}
