package com.github.notes.api.invitation.service.entity;

import com.github.notes.api.common.config.constant.InvitationStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("invitation")
public class Invitation {

    @Id
    @Field(name = "id")
    private ObjectId id;

    @Field(name = "owner_user_id")
    private String ownerUserId;

    @Field(name = "note_package_id")
    private String notePackageId;

    @Field(name = "note_package_name")
    private String notePackageName;

    @Field(name = "guest_user_id")
    private String guestUserId;

    @Field(name = "status")
    private InvitationStatus status;
}
