package to.mattias.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.constants.NoteType;
import to.mattias.entities.Note;
import to.mattias.services.NoteService;

import java.io.IOException;


/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/note")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class NoteController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoteService service;

    @RequestMapping(value = "/{projectId}/{noteId}", method = RequestMethod.GET)
    @PreAuthorize("@securityService.hasRole(#projectId,'ALL')")
    public Note findOne(@PathVariable int projectId, @PathVariable int noteId) {
        return service.findById(noteId);
    }

    @PostMapping(value = "/{projectId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Note save(@PathVariable int projectId, @RequestBody Note note) {
        return service.save(note);
    }

    @RequestMapping(value = "/{projectId}/{noteId}", method = RequestMethod.DELETE)
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public void delete(@PathVariable int projectId,@PathVariable int noteId) {
        service.delete(noteId);
    }

    @PutMapping(value = "/{projectId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Note update(@PathVariable int projectId,@RequestBody Note note) {
        return service.update(note);
    }

    @RequestMapping(value = "/createNote/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("@securityService.hasRole(#projectId, 'USERADMIN')")
    public Note newNote(@PathVariable int projectId,
                        @RequestParam("notable") int notableId,
                        @RequestParam("file") MultipartFile[] file,
                        @RequestParam("noteType") NoteType noteType,
                        @RequestParam("noteContent") String content,
                        @RequestParam("noteCreator") int noteCreator) {
        if (file.length < 1) {
            return service.newNoteWithoutFile(notableId, noteType, content, noteCreator);
        } else {
            return service.newNoteWithFile(projectId, notableId, file[0], noteCreator);
        }
    }
}
