package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.repositories.TaskRepository;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;
}