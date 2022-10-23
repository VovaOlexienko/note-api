package com.github.notes.api.notes.service.entity;

import com.github.notes.api.common.config.constant.NoteStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Note {

    @Field(value = "name")
    private String name;

    @Field(value = "status")
    private NoteStatus status;

    @Field(value = "order")
    private Integer order;
}