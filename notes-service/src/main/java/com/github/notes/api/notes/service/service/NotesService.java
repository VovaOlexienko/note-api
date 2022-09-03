package com.github.notes.api.notes.service.service;

import com.github.notes.api.common.config.dto.notes.request.CreateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NoteDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;

import java.util.List;
import java.util.UUID;

public interface NotesService {

    List<NotePackageDto> getNotePackages(Long userId);

    NotePackageDto getNotePackage(UUID notePackageId, Long userId);

    NotePackageDto createNotePackage(CreateNotePackageDto dto, Long userId);

    void updateNotePackage(UpdateNotePackageDto dto, Long userId);

    void deleteNotePackage(UUID notePackageId, Long userId);

    NoteDto createNote(UUID notePackageId, CreateNoteDto dto, Long userId);

    void updateNote(UUID notePackageId, UpdateNoteDto dto, Long userId);

    void deleteNote(UUID notePackageId, UUID noteId, Long userId);

    void addNotePackageForUser(UUID notePackageId, Long ownerUserId, Long guestUserId);

    void deleteNotePackageForUser(UUID notePackageId, Long userId);
}
