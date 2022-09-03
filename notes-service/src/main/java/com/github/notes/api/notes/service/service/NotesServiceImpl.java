package com.github.notes.api.notes.service.service;

import com.github.notes.api.common.config.constant.NoteStatus;
import com.github.notes.api.common.config.dto.notes.request.CreateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NoteDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;
import com.github.notes.api.notes.service.entity.Note;
import com.github.notes.api.notes.service.entity.NotePackage;
import com.github.notes.api.notes.service.entity.UserNotePackage;
import com.github.notes.api.notes.service.mapper.NoteServiceMapper;
import com.github.notes.api.notes.service.repository.NotePackageRepository;
import com.github.notes.api.notes.service.repository.NoteRepository;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotePackageRepository notePackageRepository;
    private final NoteRepository noteRepository;
    private final NoteServiceMapper noteServiceMapper;

    @Override
    public List<NotePackageDto> getNotePackages(Long userId) {
        return notePackageRepository.findByUsersUserId(userId).stream()
                .map(noteServiceMapper::toDto)
                .toList();
    }

    @Override
    public NotePackageDto getNotePackage(UUID notePackageId, Long userId) {
        return noteServiceMapper.toDto(getNotePackageOrThrowException(notePackageId, userId));
    }

    @Override
    public NotePackageDto createNotePackage(CreateNotePackageDto dto, Long userId) {
        NotePackage notePackage = noteServiceMapper.toEntity(dto);
        notePackage.setUsers(List.of(UserNotePackage.builder().userId(userId).notePackage(notePackage).build()));
        return noteServiceMapper.toDto(notePackageRepository.save(notePackage));
    }

    @Override
    public void updateNotePackage(UpdateNotePackageDto dto, Long userId) {
        NotePackage notePackage = getNotePackageOrThrowException(dto.getId(), userId);
        notePackage.setName(dto.getName());
        notePackageRepository.save(notePackage);
    }

    @Override
    public void deleteNotePackage(UUID notePackageId, Long userId) {
        notePackageRepository.delete(getNotePackageOrThrowException(notePackageId, userId));
    }

    @Override
    public NoteDto createNote(UUID notePackageId, CreateNoteDto dto, Long userId) {
        NotePackage notePackage = getNotePackageOrThrowException(notePackageId, userId);
        Note note = noteServiceMapper.toEntity(dto);
        note.setStatus(NoteStatus.ACTIVE);
        note.setNotePackage(notePackage);
        note.setOrder(notePackage.getNotes().size());
        return noteServiceMapper.toDto(noteRepository.save(note));
    }

    @Override
    public void updateNote(UUID notePackageId, UpdateNoteDto dto, Long userId) {
        List<Note> notes = getNotePackageOrThrowException(notePackageId, userId).getNotes();
        validateNoteOrder(notes, dto);
        Note note = getNoteFromPackageOrThrowException(notes, dto.getId());
        BeanUtils.copyProperties(dto, note);
        changeNotesOrder(dto, notes, note);
        noteRepository.saveAll(notes);
    }

    @Override
    public void deleteNote(UUID notePackageId, UUID noteId, Long userId) {
        noteRepository.delete(getNoteFromPackageOrThrowException(getNotePackageOrThrowException(notePackageId, userId).getNotes(), noteId));
    }

    @Override
    public void addNotePackageForUser(UUID notePackageId, Long ownerUserId, Long guestUserId) {
        NotePackage notePackage = getNotePackageOrThrowException(notePackageId, ownerUserId);
        notePackage.getUsers().add(UserNotePackage.builder().userId(guestUserId).notePackage(notePackage).build());
        notePackageRepository.save(notePackage);
    }

    @Override
    public void deleteNotePackageForUser(UUID notePackageId, Long userId) {
        NotePackage notePackage = getNotePackageOrThrowException(notePackageId, userId);
        notePackage.getUsers().removeIf(user -> userId.equals(user.getUserId()));
        if (CollectionUtils.isNotEmpty(notePackage.getUsers())) {
            notePackageRepository.save(notePackage);
        } else {
            notePackageRepository.delete(notePackage);
        }
    }

    private void changeNotesOrder(UpdateNoteDto dto, List<Note> notes, Note note) {
        notes.sort(Comparator.comparing(Note::getOrder));
        notes.add(dto.getOrder(), noteServiceMapper.copy(note));
        notes.remove(note);
        for (int i = 0; i < notes.size(); i++) {
            notes.get(i).setOrder(i);
        }
    }

    private void validateNoteOrder(List<Note> notes, UpdateNoteDto dto) {
        if (Integer.valueOf(notes.size()).compareTo(dto.getOrder()) <= 0
                || Integer.valueOf(0).compareTo(dto.getOrder()) > 0) {
            throw new ValidationException(String.format("Invalid order = [%s] for notePackage with size = [%s]", dto.getOrder(), notes.size()));
        }
    }

    private Note getNoteFromPackageOrThrowException(List<Note> notes, UUID noteId) {
        return notes.stream()
                .filter(n -> n.getId().equals(noteId)).findFirst()
                .orElseThrow(() -> new ValidationException(String.format("Note with id = [%s] is not found", noteId)));
    }

    private NotePackage getNotePackageOrThrowException(UUID notePackageId, Long userId) {
        return notePackageRepository.findByIdAndUsersUserId(notePackageId, userId)
                .orElseThrow(() -> new ValidationException(String.format("NotePackage with notePackageId = [%s] is not found", notePackageId)));
    }
}
