package to.mattias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import to.mattias.entities.User;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
