package com.github.notes.api.notes.service.controller;

import com.github.notes.api.notes.service.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "OwnerController", description = "Група сервісів для керування доступу користувачів до груп заміток")
public class OwnerController {

    private final NotesService notesService;

    @PutMapping(path = "/package/{notePackageId}/user/{guestUserId}")
    @Operation(description = "Відкрити користувачу доступ до пакету заміток")
    public void addNotePackageForUser(@PathVariable(name = "notePackageId") String notePackageId, @PathVariable(name = "guestUserId") String guestUserId,
                                      @RequestHeader("userId") String userId) {
        notesService.addNotePackageForUser(notePackageId, userId, guestUserId);
    }

    @DeleteMapping(path = "/package/{notePackageId}/user")
    @Operation(description = "Вийти з пакету заміток")
    public void deleteNotePackageForUser(@PathVariable(name = "notePackageId") String notePackageId, @RequestHeader("userId") String userId) {
        notesService.deleteNotePackageForUser(notePackageId, userId);
    }
}
