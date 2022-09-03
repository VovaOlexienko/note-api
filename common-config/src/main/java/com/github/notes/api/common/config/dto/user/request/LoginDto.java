package com.github.notes.api.common.config.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.github.notes.api.common.config.constant.ApiConstants.EMAIL;
import static com.github.notes.api.common.config.constant.ApiConstants.PASSWORD;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_EMAIL_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_EMAIL_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_PASSWORD_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_PASSWORD_TITLE;
import static com.github.notes.api.common.config.constant.RegexConstants.PASSWORD_REGEX;

@Data
public class LoginDto {

    @NotBlank
    @Email
    @JsonProperty(EMAIL)
    @Schema(required = true, title = USER_EMAIL_TITLE, example = USER_EMAIL_EXAMPLE)
    private String email;

    @NotBlank
    @Pattern(regexp = PASSWORD_REGEX)
    @JsonProperty(PASSWORD)
    @Schema(required = true, title = USER_PASSWORD_TITLE, example = USER_PASSWORD_EXAMPLE)
    private String password;
}
