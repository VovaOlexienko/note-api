package com.github.notes.api.notes.service.controller;

import com.github.notes.api.notes.service.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "UserController", description = "Група сервісів для керування доступу користувачів до груп заміток")
public class UserController {

    private final NotesService notesService;

    @PutMapping(path = "/package/{notePackageId}/user/{guestUserId}")
    @Operation(description = "Відкрити користувачу доступ до пакету заміток")
    public void addNotePackageForUser(@PathVariable(name = "notePackageId") UUID notePackageId, @PathVariable(name = "guestUserId") Long guestUserId,
                                      @RequestHeader("userId") Long userId) {
        notesService.addNotePackageForUser(notePackageId, userId, guestUserId);
    }

    @DeleteMapping(path = "/package/{notePackageId}/user")
    @Operation(description = "Вийти з пакету заміток")
    public void deleteNotePackageForUser(@PathVariable(name = "notePackageId") UUID notePackageId, @RequestHeader("userId") Long userId) {
        notesService.deleteNotePackageForUser(notePackageId, userId);
    }
}
