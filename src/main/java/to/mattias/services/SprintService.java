package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Sprint;
import to.mattias.repositories.SprintRepository;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class SprintService {

    @Autowired
    private SprintRepository repo;

    public Sprint findById(int sprintId) {
        return repo.findOne(sprintId);
    }

    public Sprint save(Sprint sprintToSave) {
        return repo.save(sprintToSave);
    }


}
