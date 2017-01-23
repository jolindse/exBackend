package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Project;
import to.mattias.repositories.ProjectRepository;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

}
