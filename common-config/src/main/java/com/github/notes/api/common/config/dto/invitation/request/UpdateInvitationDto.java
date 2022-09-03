package com.github.notes.api.common.config.dto.invitation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.notes.api.common.config.constant.InvitationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.github.notes.api.common.config.constant.ApiConstants.ID;
import static com.github.notes.api.common.config.constant.ApiConstants.STATUS;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_STATUS_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.INVITATION_STATUS_TITLE;

@Data
public class UpdateInvitationDto {

    @NotNull
    @JsonProperty(ID)
    @Schema(required = true, title = INVITATION_ID_TITLE, example = INVITATION_ID_EXAMPLE)
    private UUID id;

    @NotNull
    @JsonProperty(STATUS)
    @Schema(required = true, title = INVITATION_STATUS_TITLE, example = INVITATION_STATUS_EXAMPLE)
    private InvitationStatus status;
}
