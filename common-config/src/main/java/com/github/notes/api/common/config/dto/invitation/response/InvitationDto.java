package com.github.notes.api.common.config.dto.invitation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.notes.api.common.config.constant.InvitationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTE_PACKAGE_NAME;
import static com.github.notes.api.common.config.constant.ApiConstants.STATUS;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_STATUS_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_STATUS_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_NAME_TITLE;

@Data
public class InvitationDto {

    @JsonProperty(ID)
    @Schema(required = true, title = INVITATION_ID_TITLE, example = INVITATION_ID_EXAMPLE)
    private String id;

    @JsonProperty(NOTE_PACKAGE_NAME)
    @Schema(required = true, title = NOTE_PACKAGE_NAME_TITLE, example = NOTE_PACKAGE_NAME_EXAMPLE)
    private String notePackageName;

    @JsonProperty(STATUS)
    @Schema(required = true, title = INVITATION_STATUS_TITLE, example = INVITATION_STATUS_EXAMPLE)
    private InvitationStatus status;
}
