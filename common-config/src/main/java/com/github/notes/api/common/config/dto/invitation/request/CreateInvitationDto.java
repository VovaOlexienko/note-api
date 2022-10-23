package com.github.notes.api.common.config.dto.invitation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.github.notes.api.common.config.constant.ApiConstants.GUEST_USER_EMAIL;
import static com.github.notes.api.common.config.constant.ApiConstants.NOTE_PACKAGE_ID;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.NOTE_PACKAGE_ID_TITLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_EMAIL_EXAMPLE;
import static com.github.notes.api.common.config.constant.OpenApiConstants.USER_EMAIL_TITLE;

@Data
public class CreateInvitationDto {

    @NotNull
    @JsonProperty(NOTE_PACKAGE_ID)
    @Schema(required = true, title = NOTE_PACKAGE_ID_TITLE, example = NOTE_PACKAGE_ID_EXAMPLE)
    private String notePackageId;

    @NotBlank
    @JsonProperty(GUEST_USER_EMAIL)
    @Schema(required = true, title = USER_EMAIL_TITLE, example = USER_EMAIL_EXAMPLE)
    private String guestUserEmail;
}
