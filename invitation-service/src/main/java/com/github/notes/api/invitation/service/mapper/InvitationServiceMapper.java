package com.github.notes.api.invitation.service.mapper;

import com.github.notes.api.common.config.dto.invitation.request.CreateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.response.InvitationDto;
import com.github.notes.api.invitation.service.entity.Invitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvitationServiceMapper {

    Invitation toEntity(CreateInvitationDto dto);

    @Mapping(ignore = true, target = "id")
    InvitationDto toDto(Invitation entity);
}
