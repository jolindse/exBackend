package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.repositories.ProjectRepository;
import to.mattias.repositories.UserRepository;

/**
 * Created by juan on 2017-02-27.
 */

@Service
public class SecurityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    public boolean hasAuthority(int projId, String username) {
        return true;
    }
}
