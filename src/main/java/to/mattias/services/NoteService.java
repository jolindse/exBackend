package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Notable;
import to.mattias.entities.Note;
import to.mattias.repositories.NoteRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public Note save(Note note) {
        return repo.save(note);
    }

    public Note findById(int id) {
        return repo.findOne(id);
    }

    public Note update(Note note) {
        return save(note);
    }

    public void delete(int noteId) {
        repo.delete(noteId);
    }

    public List<Note> findByNotable(Notable notable) {
        return repo.findAll().stream()
            .filter(note -> note.getNoteAssignedTo().contains(notable))
            .collect(Collectors.toList());
    }


}
