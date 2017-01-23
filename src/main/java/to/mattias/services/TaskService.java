package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Task;
import to.mattias.repositories.TaskRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task save(Task task) {
        return repo.saveAndFlush(task);
    }

    public Task findById(Long taskId) {
        return repo.findOne(taskId);
    }

    public Task update(Task task) {
        return save(task);
    }

    public void delete(Long taskId) {
        repo.delete(taskId);
    }
}
