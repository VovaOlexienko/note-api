package com.github.notes.api.common.config.manager;

import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "notes-service/notes-service")
public interface NotesServiceManager {

    @GetMapping(path = "/package/{notePackageId}")
    NotePackageDto getNotePackage(@PathVariable(name = "notePackageId") UUID notePackageId,
                                  @RequestHeader("userId") Long userId);


    @PutMapping(path = "/package/{notePackageId}/user/{guestUserId}")
    void addNotePackageForUser(@PathVariable(name = "notePackageId") UUID notePackageId,
                               @PathVariable(name = "guestUserId") Long guestUserId,
                               @RequestHeader("userId") Long userId);
}
