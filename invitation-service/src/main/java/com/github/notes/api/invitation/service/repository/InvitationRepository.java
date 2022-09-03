package com.github.notes.api.invitation.service.repository;

import com.github.notes.api.invitation.service.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

    List<Invitation> findByGuestUserId(Long guestUserId);

    Optional<Invitation> findByIdAndGuestUserId(UUID id, Long guestUserId);
}
