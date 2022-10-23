package com.github.notes.api.notes.service.service;

import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.notes.service.entity.NotePackage;
import com.github.notes.api.notes.service.mapper.NoteServiceMapper;
import com.github.notes.api.notes.service.repository.NotePackageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotePackageRepository notePackageRepository;
    private final NoteServiceMapper noteServiceMapper;

    @Override
    public List<NotePackageDto> getNotePackages(String userId) {
        return notePackageRepository.findByOwnersContains(userId).stream()
                .map(noteServiceMapper::toDto)
                .toList();
    }

    @Override
    public NotePackageDto getNotePackage(String notePackageId, String userId) {
        return noteServiceMapper.toDto(getNotePackageOrThrowException(notePackageId, userId));
    }

    @Override
    public NotePackageDto createNotePackage(CreateNotePackageDto dto, String userId) {
        NotePackage notePackage = noteServiceMapper.toEntity(dto);
        notePackage.setOwners(List.of(userId));
        return noteServiceMapper.toDto(notePackageRepository.save(notePackage));
    }

    @Override
    public void updateNotePackage(UpdateNotePackageDto dto, String userId) {
        NotePackage oldNotePackage = getNotePackageOrThrowException(dto.getId(), userId);
        NotePackage notePackage = noteServiceMapper.toEntity(dto);
        notePackage.setOwners(oldNotePackage.getOwners());
        notePackageRepository.save(notePackage);
    }

    @Override
    public void deleteNotePackage(String notePackageId, String userId) {
        notePackageRepository.delete(getNotePackageOrThrowException(notePackageId, userId));
    }

    @Override
    public void addNotePackageForUser(String notePackageId, String ownerUserId, String guestUserId) {
        NotePackage notePackage = getNotePackageOrThrowException(notePackageId, ownerUserId);
        notePackage.getOwners().add(guestUserId);
        notePackageRepository.save(notePackage);
    }

    @Override
    public void deleteNotePackageForUser(String notePackageId, String userId) {
        NotePackage notePackage = getNotePackageOrThrowException(notePackageId, userId);
        notePackage.getOwners().removeIf(userId::equals);
        if (CollectionUtils.isNotEmpty(notePackage.getOwners())) {
            notePackageRepository.save(notePackage);
        } else {
            notePackageRepository.delete(notePackage);
        }
    }

    private NotePackage getNotePackageOrThrowException(String notePackageId, String userId) {
        return notePackageRepository.findByIdAndOwnersContains(notePackageId, userId)
                .orElseThrow(() -> new ValidationException(String.format("NotePackage with notePackageId = [%s] is not found", notePackageId)));
    }
}
