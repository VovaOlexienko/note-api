package com.github.notes.api.notes.service.service;

import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;

import java.util.List;

public interface NotesService {

    List<NotePackageDto> getNotePackages(String userId);

    NotePackageDto getNotePackage(String notePackageId, String userId);

    NotePackageDto createNotePackage(CreateNotePackageDto dto, String userId);

    void updateNotePackage(UpdateNotePackageDto dto, String userId);

    void deleteNotePackage(String notePackageId, String userId);

    void addNotePackageForUser(String notePackageId, String ownerUserId, String guestUserId);

    void deleteNotePackageForUser(String notePackageId, String userId);
}
