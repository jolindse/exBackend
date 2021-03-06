package to.mattias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
public interface SprintRepository extends JpaRepository<Sprint, Integer> {

    public Sprint findBySprintTasks(Task task);
    public Sprint findBySprintTitle(String title);
}
