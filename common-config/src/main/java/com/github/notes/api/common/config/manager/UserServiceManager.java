package com.github.notes.api.common.config.manager;

import com.github.notes.api.common.config.dto.user.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service/user-service")
public interface UserServiceManager {

    @GetMapping(value = "/user/email/{email}")
    UserDto getUser(@PathVariable("email") String email);
}
