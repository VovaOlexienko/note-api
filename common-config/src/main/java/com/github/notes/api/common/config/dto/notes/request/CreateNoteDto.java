package com.github.notes.api.common.config.dto.notes.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTIFICATION_TIME;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NOTIFICATION_TIME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NOTIFICATION_TIME_TITLE;

@Data
public class CreateNoteDto {

    @NotBlank
    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_NAME_TITLE, example = NOTE_NAME_EXAMPLE)
    private String name;

    @JsonProperty(NOTIFICATION_TIME)
    @Schema(title = NOTE_NOTIFICATION_TIME_TITLE, example = NOTE_NOTIFICATION_TIME_EXAMPLE)
    private LocalDateTime notificationTime;
}
