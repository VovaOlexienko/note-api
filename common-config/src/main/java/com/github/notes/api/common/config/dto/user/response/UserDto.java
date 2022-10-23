package com.github.notes.api.common.config.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_ID_TITLE;

@Data
public class UserDto {

    @JsonProperty(ID)
    @Schema(required = true, title = USER_ID_TITLE, example = USER_ID_EXAMPLE)
    private String id;
}
