package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Note;
import to.mattias.services.NoteService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/note")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class NoteController {

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
}
