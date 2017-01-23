package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Note;
import to.mattias.repositories.NoteRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository repo;

    public List<Note> findAll() {
        return repo.findAll();
    }
}
