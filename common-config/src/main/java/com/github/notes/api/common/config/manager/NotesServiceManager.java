package com.github.notes.api.common.config.manager;

import com.github.notes.api.common.config.dto.notes.response.NotePackageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "notes-service/notes-service")
public interface NotesServiceManager {

    @GetMapping(path = "/package/{notePackageId}")
    NotePackageDto getNotePackage(@PathVariable(name = "notePackageId") String notePackageId,
                                  @RequestHeader("userId") String userId);


    @PutMapping(path = "/package/{notePackageId}/user/{guestUserId}")
    void addNotePackageForUser(@PathVariable(name = "notePackageId") String notePackageId,
                               @PathVariable(name = "guestUserId") String guestUserId,
                               @RequestHeader("userId") String userId);
}
