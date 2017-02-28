package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import to.mattias.constants.UserRole;
import to.mattias.entities.Role;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;
import to.mattias.security.jwt.AuthenticatedUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by juan on 2017-02-27.
 */

@Component("securityService")
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    public boolean hasRole(int projId, UserRole[] role) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUserName(user.getUsername());
        return currUser.isAuthorized(projId, role);
    }

    public boolean hasRole(UserRole[] role) {
        AuthenticatedUser user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUserName(user.getName());

        return Arrays.asList(role).contains(currUser.getMainRole());
    }
}
