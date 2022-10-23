package com.github.notes.api.notes.service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("note_package")
public class NotePackage {

    @Id
    @Field(value = "id")
    private String id;

    @Field(value = "name")
    private String name;

    @Field(value = "notes")
    private List<Note> notes;

    @Field(value = "owners")
    private List<String> owners;
}