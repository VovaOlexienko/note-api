package com.github.notes.api.notes.service.repository;

import com.github.notes.api.notes.service.entity.NotePackage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotePackageRepository extends MongoRepository<NotePackage, String> {

    List<NotePackage> findByOwnersContains(String userId);

    Optional<NotePackage> findByIdAndOwnersContains(ObjectId id, List<String> ownerId);
}
