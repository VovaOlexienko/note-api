package com.github.notes.api.invitation.service.mapper;

import com.github.notes.api.common.config.dto.invitation.request.CreateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.response.InvitationDto;
import com.github.notes.api.invitation.service.entity.Invitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvitationServiceMapper {

    Invitation toEntity(CreateInvitationDto dto);

    InvitationDto toDto(Invitation entity);
}
