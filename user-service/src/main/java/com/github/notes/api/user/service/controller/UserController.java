package com.github.notes.api.user.service.controller;

import com.github.notes.api.common.config.dto.user.request.LoginDto;
import com.github.notes.api.common.config.dto.user.request.RegisterDto;
import com.github.notes.api.common.config.dto.user.response.UserDto;
import com.github.notes.api.user.service.service.UserService;
import com.github.notes.api.user.service.util.AuthorizationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserController", description = "Група сервісів для роботи з користувачами")
public class UserController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;

    @PostMapping("/register")
    @Operation(description = "Зареєструвати користувача")
    public void register(@Valid @RequestBody RegisterDto dto, HttpServletResponse response) {
        response.addCookie(authorizationUtil.createAuthorizationCookie(userService.register(dto)));
    }

    @PostMapping("/login")
    @Operation(description = "Увійти в аккаунт користувача")
    public void login(@Valid @RequestBody LoginDto dto, HttpServletResponse response) {
        response.addCookie(authorizationUtil.createAuthorizationCookie(userService.login(dto)));
    }

    @PostMapping("/logout")
    @Operation(description = "Вийти з аккаунта користувача")
    public void logout(HttpServletResponse response) {
        response.addCookie(authorizationUtil.deleteAuthorizationCookie());
    }

    @GetMapping("/user/email/{email}")
    @Operation(description = "Отримати данні користувача")
    public UserDto getUser(@Email @PathVariable("email") String email) {
        return userService.getUser(email);
    }
}
