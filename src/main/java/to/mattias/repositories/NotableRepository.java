package to.mattias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import to.mattias.entities.Notable;

/**
 * Created by mattias on 2017-03-23.
 */
public interface NotableRepository extends JpaRepository<Notable, Integer> {

}
