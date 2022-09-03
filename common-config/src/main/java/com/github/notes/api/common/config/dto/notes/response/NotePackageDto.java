package com.github.notes.api.common.config.dto.notes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTES;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_TITLE;

@Data
public class NotePackageDto {

    @JsonProperty(ID)
    @Schema(required = true, title = NOTE_PACKAGE_ID_TITLE, example = NOTE_PACKAGE_ID_EXAMPLE)
    private UUID id;

    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_PACKAGE_NAME_TITLE, example = NOTE_PACKAGE_NAME_EXAMPLE)
    private String name;

    @JsonProperty(NOTES)
    private List<NoteDto> notes;
}