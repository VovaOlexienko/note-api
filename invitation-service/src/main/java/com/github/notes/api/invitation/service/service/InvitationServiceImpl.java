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
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<InvitationDto> getInvitations(Long userId) {
        return invitationRepository.findByGuestUserId(userId)
                .stream().map(invitationServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createInvitation(CreateInvitationDto dto, Long userId) {
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
    public void updateInvitation(UpdateInvitationDto dto, Long userId) {
        Invitation invitation = getInvitationOrThrowException(dto.getId(), userId);
        validateInvitationStatus(dto, invitation);
        if (ACCEPTED == dto.getStatus()) {
            notesServiceManager.addNotePackageForUser(invitation.getNotePackageId(), invitation.getGuestUserId(), invitation.getOwnerUserId());
        }
        invitation.setStatus(dto.getStatus());
        invitationRepository.save(invitation);
    }

    private Invitation getInvitationOrThrowException(UUID invitationId, Long userId) {
        return invitationRepository.findByIdAndGuestUserId(invitationId, userId)
                .orElseThrow(() -> new ValidationException(String.format("Invitation with id = [%s] is not found", invitationId)));
    }

    private void validateInvitationStatus(UpdateInvitationDto dto, Invitation invitation) {
        if (ACTIVE != invitation.getStatus() || dto.getStatus() == invitation.getStatus()) {
            throw new ValidationException(String.format("Invitation with id = [%s] already has status = [%s]", invitation.getId(), invitation.getStatus()));
        }
    }
}
