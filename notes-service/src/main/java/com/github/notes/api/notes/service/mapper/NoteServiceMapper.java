package com.github.notes.api.notes.service.mapper;

import com.github.notes.api.common.config.dto.notes.NoteDto;
import com.github.notes.api.common.config.dto.notes.request.CreateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.request.UpdateNotePackageDto;
import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import com.github.notes.api.notes.service.entity.Note;
import com.github.notes.api.notes.service.entity.NotePackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteServiceMapper {

    @Mapping(ignore = true, target = "id")
    NotePackageDto toDto(NotePackage entity);

    NoteDto toDto(Note entity);

    NotePackage toEntity(CreateNotePackageDto dto);

    @Mapping(ignore = true, target = "id")
    NotePackage toEntity(UpdateNotePackageDto dto);
}
