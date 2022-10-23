package com.github.notes.api.invitation.service.controller;

import com.github.notes.api.common.config.dto.invitation.request.CreateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.request.UpdateInvitationDto;
import com.github.notes.api.common.config.dto.invitation.response.InvitationDto;
import com.github.notes.api.invitation.service.service.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "InvitationController", description = "Група сервісів для роботи з запрошеннями")
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping(path = "/invitation")
    @Operation(description = "Створити запрошення")
    public List<InvitationDto> createInvitation(@RequestHeader("userId") String userId) {
        return invitationService.getInvitations(userId);
    }

    @PostMapping(path = "/invitation")
    @Operation(description = "Створити запрошення")
    public void createInvitation(@Valid @RequestBody CreateInvitationDto dto, @RequestHeader("userId") String userId) {
        invitationService.createInvitation(dto, userId);
    }

    @PutMapping(path = "/invitation")
    @Operation(description = "Прийняти або відхилити запрошення")
    public void updateInvitation(@Valid @RequestBody UpdateInvitationDto dto, @RequestHeader("userId") String userId) {
        invitationService.updateInvitation(dto, userId);
    }
}
