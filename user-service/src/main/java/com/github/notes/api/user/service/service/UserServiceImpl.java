package com.github.notes.api.user.service.service;

import com.github.notes.api.common.config.dto.user.request.LoginDto;
import com.github.notes.api.common.config.dto.user.request.RegisterDto;
import com.github.notes.api.common.config.dto.user.response.UserDto;
import com.github.notes.api.user.service.entity.User;
import com.github.notes.api.user.service.mapper.UserServiceMapper;
import com.github.notes.api.user.service.repository.UserRepository;
import com.github.notes.api.user.service.util.AuthorizationUtil;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorizationUtil authorizationUtil;
    private final UserServiceMapper userServiceMapper;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new SecureRandom();

    @Override
    public String register(RegisterDto dto) {
        User user = userServiceMapper.toEntity(dto);
        user.setSalt(generateSalt());
        user.setHash(passwordEncoder.encode(dto.getPassword() + user.getSalt()));
        return authorizationUtil.generateJwtToken(userRepository.save(user).getId());
    }

    @Override
    public String login(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ValidationException("Authentication failed"));
        if (!passwordEncoder.matches(dto.getPassword() + user.getSalt(), user.getHash())) {
            throw new ValidationException("Authentication failed");
        }
        return authorizationUtil.generateJwtToken(user.getId());
    }

    @Override
    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException(String.format("User with email = [%s] is not found", email)));
        return userServiceMapper.toDto(user);
    }

    public String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
