package com.github.notes.api.notes.service.repository;

import com.github.notes.api.notes.service.entity.NotePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotePackageRepository extends JpaRepository<NotePackage, UUID> {

    List<NotePackage> findByUsersUserId(Long userId);

    Optional<NotePackage> findByIdAndUsersUserId(UUID id, Long userId);
}
