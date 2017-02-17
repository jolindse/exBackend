package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Note;
import to.mattias.services.NoteService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @GetMapping
    public List<Note> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    public Note findOne(@PathVariable int noteId) {
        return service.findById(noteId);
    }

    @PostMapping
    public Note save(@RequestBody Note note) {
        return service.save(note);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int noteId) {
        service.delete(noteId);
    }

    @PutMapping
    public Note update(@RequestBody Note note) {
        return service.update(note);
    }
}
