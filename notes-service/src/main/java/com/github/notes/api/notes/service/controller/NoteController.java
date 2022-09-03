package com.github.notes.api.notes.service.controller;

import com.github.notes.api.common.config.dto.notes.request.CreateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNoteDto;
import com.github.notes.api.common.config.dto.notes.response.NoteDto;
import com.github.notes.api.notes.service.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "NoteController", description = "Група сервісів для роботи з замітками")
public class NoteController {

    private final NotesService notesService;

    @PostMapping(path = "/package/{notePackageId}/note")
    @Operation(description = "Створити замітку")
    public NoteDto createNote(@RequestBody @Valid CreateNoteDto dto, @PathVariable(name = "notePackageId") UUID notePackageId,
                              @RequestHeader("userId") Long userId) {
        return notesService.createNote(notePackageId, dto, userId);
    }

    @PutMapping(path = "/package/{notePackageId}/note")
    @Operation(description = "Редагувати замітку")
    public void updateNote(@RequestBody @Valid UpdateNoteDto dto, @PathVariable(name = "notePackageId") UUID notePackageId,
                           @RequestHeader("userId") Long userId) {
        notesService.updateNote(notePackageId, dto, userId);
    }

    @DeleteMapping(path = "/package/{notePackageId}/note/{noteId}")
    @Operation(description = "Видалити замітку")
    public void deleteNote(@PathVariable(name = "notePackageId") UUID notePackageId, @PathVariable(name = "noteId") UUID noteId,
                           @RequestHeader("userId") Long userId) {
        notesService.deleteNote(notePackageId, noteId, userId);
    }
}
