package com.github.notes.api.invitation.service.repository;

import com.github.notes.api.invitation.service.entity.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends MongoRepository<Invitation, String> {

    List<Invitation> findByGuestUserId(String guestUserId);

    Optional<Invitation> findByIdAndGuestUserId(String id, String guestUserId);
}
