package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
