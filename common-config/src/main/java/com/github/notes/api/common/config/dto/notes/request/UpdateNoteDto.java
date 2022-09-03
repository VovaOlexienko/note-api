package com.github.notes.api.common.config.dto.notes.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.notes.api.common.config.constant.NoteStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTIFICATION_TIME;
import static com.github.notes.api.common.config.constant.ApiConstants.ORDER;
import static com.github.notes.api.common.config.constant.ApiConstants.STATUS;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NOTIFICATION_TIME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NOTIFICATION_TIME_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ORDER_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ORDER_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_STATUS_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_STATUS_TITLE;

@Data
public class UpdateNoteDto {

    @NotNull
    @JsonProperty(ID)
    @Schema(required = true, title = NOTE_ID_TITLE, example = NOTE_ID_EXAMPLE)
    private UUID id;

    @NotBlank
    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_NAME_TITLE, example = NOTE_NAME_EXAMPLE)
    private String name;

    @NotNull
    @JsonProperty(STATUS)
    @Schema(required = true, title = NOTE_STATUS_TITLE, example = NOTE_STATUS_EXAMPLE)
    private NoteStatus status;

    @JsonProperty(NOTIFICATION_TIME)
    @Schema(title = NOTE_NOTIFICATION_TIME_TITLE, example = NOTE_NOTIFICATION_TIME_EXAMPLE)
    private LocalDateTime notificationTime;

    @NotNull
    @JsonProperty(ORDER)
    @Schema(required = true, title = NOTE_ORDER_TITLE, example = NOTE_ORDER_EXAMPLE)
    private Integer order;
}
