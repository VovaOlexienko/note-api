package com.github.notes.api.user.service.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("user")
public class User {

    @Id
    @Field(name = "id")
    private ObjectId id;

    @Indexed(unique = true)
    @Field(value = "username")
    private String username;

    @Indexed(unique = true)
    @Field(value = "email")
    private String email;

    @Field(value = "hash")
    private String hash;

    @Field(value = "salt")
    private String salt;
}