package com.github.notes.api.notes.service.controller;

import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.notes.service.service.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "NotePackageController", description = "Група сервісів для роботи з пакетами заміток")
public class NotePackageController {

    private final NotesService notesService;

    @GetMapping(path = "/package")
    @Operation(description = "Отримати всі пакети заміток користувача")
    public List<NotePackageDto> getNotePackages(@RequestHeader("userId") Long userId) {
        return notesService.getNotePackages(userId);
    }

    @GetMapping(path = "/package/{notePackageId}")
    @Operation(description = "Отримати пакет заміток")
    public NotePackageDto getNotePackage(@PathVariable(name = "notePackageId") UUID notePackageId, @RequestHeader("userId") Long userId) {
        return notesService.getNotePackage(notePackageId, userId);
    }

    @PostMapping(path = "/package")
    @Operation(description = "Створити пакет заміток")
    public NotePackageDto createNotePackage(@Valid @RequestBody CreateNotePackageDto dto, @RequestHeader("userId") Long userId) {
        return notesService.createNotePackage(dto, userId);
    }

    @PutMapping(path = "/package")
    @Operation(description = "Редагувати пакет заміток")
    public void updateNotePackage(@Valid @RequestBody UpdateNotePackageDto dto, @RequestHeader("userId") Long userId) {
        notesService.updateNotePackage(dto, userId);
    }

    @DeleteMapping(path = "/package/{notePackageId}")
    @Operation(description = "Видалити пакет заміток")
    public void deleteNotePackage(@PathVariable(name = "notePackageId") UUID notePackageId, @RequestHeader("userId") Long userId) {
        notesService.deleteNotePackage(notePackageId, userId);
    }
}
