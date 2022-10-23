package com.github.notes.api.common.config.dto.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.notes.api.common.config.constant.NoteStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.ORDER;
import static com.github.notes.api.common.config.constant.ApiConstants.STATUS;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_NAME_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ORDER_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_ORDER_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_STATUS_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_STATUS_TITLE;

@Data
public class NoteDto {

    @NotBlank
    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_NAME_TITLE, example = NOTE_NAME_EXAMPLE)
    private String name;

    @NotNull
    @JsonProperty(STATUS)
    @Schema(required = true, title = NOTE_STATUS_TITLE, example = NOTE_STATUS_EXAMPLE)
    private NoteStatus status;

    @Min(0)
    @JsonProperty(ORDER)
    @Schema(required = true, title = NOTE_ORDER_TITLE, example = NOTE_ORDER_EXAMPLE)
    private Long order;
}