package to.mattias.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Project;
import to.mattias.entities.Task;
import to.mattias.repositories.ProjectRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    public List<Project> findAll() {
        return repo.findAll();
    }

    public Project save(Project project) {
        return repo.save(project);
    }

    public Project findById(int projectId) {
        return repo.findOne(projectId);
    }

    public Project update(Project project) {
        return save(project);
    }

    public void delete(int projectId) {
        repo.delete(projectId);
    }

    public void addTask(int projectId, Task task) {
        Project project = findById(projectId);
        project.addTask(task);
        save(project);
    }

}
