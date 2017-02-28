package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import to.mattias.constants.UserAction;
import to.mattias.entities.Role;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;
import to.mattias.security.jwt.AuthenticatedUser;

import java.util.List;

/**
 * Created by juan on 2017-02-27.
 */

@Component("securityService")
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private ProjectRepository projectRepository;*/

    public boolean hasAuthority(int projId, UserAction userAction) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUserName(user.getUsername());
        return currUser.isAuthorized(projId, userAction);
    }

    public boolean hasRole(String[] role) {
        AuthenticatedUser user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUserName(user.getName());

        boolean found = false;

        List<Role> roleList = currUser.getRoleList();

        for (int j = 0; j < roleList.size(); j++) {
            for (int i = 0; i < role.length; i++) {
                if (roleList.get(j).getName().equals(role[i])) {
                    found = true;
                }
            }
        }
        return found;
    }
}
