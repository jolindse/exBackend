package to.mattias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findByProjectTitle(String projectTitle);

    Project findByProjectDescription(String projectDescription);

    Project findByProjectTasks(Task task);

    public Project findByProjectSprints(Sprint sprint);
}
