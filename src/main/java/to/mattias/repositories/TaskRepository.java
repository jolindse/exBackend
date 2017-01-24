package to.mattias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import to.mattias.entities.Task;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
