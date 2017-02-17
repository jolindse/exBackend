package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.NoteObj;
import to.mattias.repositories.NoteObjRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class NoteObjService {

    @Autowired
    private NoteObjRepository repo;

    public NoteObj findById(int id) {
        return repo.findOne(id);
    }

    public List<NoteObj> findAll() {
        return repo.findAll();
    }

    public NoteObj save(NoteObj noteObj) {
        return repo.save(noteObj);
    }

    public NoteObj update(NoteObj noteObj) {
        return save(noteObj);
    }

    public void delete(int id) {
        repo.delete(id);
    }
}
