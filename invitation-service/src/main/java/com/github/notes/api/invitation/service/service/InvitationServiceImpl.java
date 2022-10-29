package com.github.notes.api.invitation.service.service;

import com.github.notes.api.common.config.dto.invitation.request.CreateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.request.UpdateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.response.InvitationDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.common.config.dto.user.response.UserDto;
import com.github.notes.api.common.config.manager.NotesServiceManager;
import com.github.notes.api.common.config.manager.UserServiceManager;
import com.github.notes.api.invitation.service.entity.Invitation;
import com.github.notes.api.invitation.service.mapper.InvitationServiceMapper;
import com.github.notes.api.invitation.service.repository.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

import static com.github.notes.api.common.config.constant.InvitationStatus.ACCEPTED;
import static com.github.notes.api.common.config.constant.InvitationStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final NotesServiceManager notesServiceManager;
    private final UserServiceManager userServiceManager;
    private final InvitationServiceMapper invitationServiceMapper;

    @Override
    public List<InvitationDto> getInvitations(String userId) {
        return invitationRepository.findByGuestUserId(userId).stream()
                .map(entity -> {
                    InvitationDto invitationDto = invitationServiceMapper.toDto(entity);
                    invitationDto.setId(entity.getId().toString());
                    return invitationDto;
                })
                .toList();
    }

    @Override
    public void createInvitation(CreateInvitationDto dto, String userId) {
        UserDto user = userServiceManager.getUser(dto.getGuestUserEmail());
        if (user.getId().equals(userId)) {
            throw new ValidationException("Can't create invitation for user with same id");
        }
        NotePackageDto notePackage = notesServiceManager.getNotePackage(dto.getNotePackageId(), userId);
        Invitation invitation = invitationServiceMapper.toEntity(dto);
        invitation.setOwnerUserId(userId);
        invitation.setGuestUserId(user.getId());
        invitation.setNotePackageName(notePackage.getName());
        invitation.setStatus(ACTIVE);
        invitationRepository.save(invitation);
    }

    @Override
    public void updateInvitation(UpdateInvitationDto dto, String userId) {
        Invitation invitation = getInvitationOrThrowException(dto.getId(), userId);
        validateInvitationStatus(dto, invitation);
        if (ACCEPTED == dto.getStatus()) {
            notesServiceManager.addNotePackageForUser(invitation.getNotePackageId(), invitation.getGuestUserId(), invitation.getOwnerUserId());
        }
        invitation.setStatus(dto.getStatus());
        invitationRepository.save(invitation);
    }

    private Invitation getInvitationOrThrowException(String invitationId, String userId) {
        return invitationRepository.findByIdAndGuestUserId(invitationId, userId)
                .orElseThrow(() -> new ValidationException(String.format("Invitation with id = [%s] is not found", invitationId)));
    }

    private void validateInvitationStatus(UpdateInvitationDto dto, Invitation invitation) {
        if (ACTIVE != invitation.getStatus() || dto.getStatus() == invitation.getStatus()) {
            throw new ValidationException(String.format("Invitation with id = [%s] already has status = [%s]", invitation.getId(), invitation.getStatus()));
        }
    }
}
