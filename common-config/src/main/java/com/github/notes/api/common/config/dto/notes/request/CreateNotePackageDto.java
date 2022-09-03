package com.github.notes.api.common.config.dto.notes.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_TITLE;

@Data
public class CreateNotePackageDto {

    @NotBlank
    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_PACKAGE_NAME_TITLE, example = NOTE_PACKAGE_NAME_EXAMPLE)
    private String name;
}
