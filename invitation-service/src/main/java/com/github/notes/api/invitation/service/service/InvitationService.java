package com.github.notes.api.invitation.service.service;

import com.github.notes.api.common.config.dto.invitation.request.CreateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.request.UpdateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.response.InvitationDto;

import java.util.List;

public interface InvitationService {

    List<InvitationDto> getInvitations(String userId);

    void createInvitation(CreateInvitationDto dto, String userId);

    void updateInvitation(UpdateInvitationDto dto, String userId);
}
