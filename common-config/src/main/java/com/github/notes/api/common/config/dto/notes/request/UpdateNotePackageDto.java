package com.github.notes.api.common.config.dto.notes.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.notes.api.common.config.dto.notes.NoteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.ApiConstants.NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTES;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_TITLE;

@Data
public class UpdateNotePackageDto {

    @NotNull
    @JsonProperty(ID)
    @Schema(required = true, title = NOTE_PACKAGE_ID_TITLE, example = NOTE_PACKAGE_ID_EXAMPLE)
    private String id;

    @NotBlank
    @JsonProperty(NAME)
    @Schema(required = true, title = NOTE_PACKAGE_NAME_TITLE, example = NOTE_PACKAGE_NAME_EXAMPLE)
    private String name;

    @JsonProperty(NOTES)
    private List<NoteDto> notes;
}
