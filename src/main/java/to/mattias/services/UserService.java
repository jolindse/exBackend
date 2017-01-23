package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Service;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User saveUser(User user) {
        return repo.saveAndFlush(user);
    }

    public User findById(Long userId) {
        return repo.findOne(userId);
    }

    public User update(User user) {
        return repo.saveAndFlush(user);
    }

    public void delete(Long userId) {
        repo.delete(userId);
    }
}
