package com.github.notes.api.common.config.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import static com.github.notes.api.common.config.constant.ApiConstants.ERROR_MESSAGE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.ERROR_MESSAGE_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.ERROR_MESSAGE_TITLE;

public record ErrorDto(

        @JsonProperty(ERROR_MESSAGE)
        @Schema(required = true, title = ERROR_MESSAGE_TITLE, example = ERROR_MESSAGE_EXAMPLE)
        String message
) {
}
