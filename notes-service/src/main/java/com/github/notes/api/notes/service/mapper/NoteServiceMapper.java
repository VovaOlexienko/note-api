package com.github.notes.api.notes.service.mapper;

import com.github.notes.api.common.config.dto.notes.request.CreateNoteDto;
import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NoteDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.notes.service.entity.Note;
import com.github.notes.api.notes.service.entity.NotePackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteServiceMapper {

    NotePackageDto toDto(NotePackage entity);

    NoteDto toDto(Note entity);

    NotePackage toEntity(CreateNotePackageDto dto);

    Note toEntity(CreateNoteDto dto);

    Note copy(Note dto);
}
