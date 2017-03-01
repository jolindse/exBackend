package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import to.mattias.constants.UserRole;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;
import to.mattias.security.jwt.AuthenticatedUser;

import java.util.Arrays;

/**
 * Created by juan on 2017-02-27.
 */

@Component("securityService")
public class SecurityService {

    private UserRole[] AdminAccess = {UserRole.ADMIN};
    private UserRole[] AdminUserAccess = {UserRole.USER, UserRole.ADMIN};
    private UserRole[] AdminUserCustomerAccess = {UserRole.CUSTOMER, UserRole.USER, UserRole.ADMIN};


    @Autowired
    private UserRepository userRepository;

    public boolean hasRole(int projId, String accessQualifier) {
        AuthenticatedUser user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUsername(user.getName());
        return currUser.isAuthorized(projId, getRole(accessQualifier));
    }

    public boolean hasRole(String accessQualifier) {
        AuthenticatedUser user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUsername(user.getName());

        return Arrays.asList(getRole(accessQualifier)).contains(currUser.getMainRole());
    }

    private UserRole[] getRole(String accessQualifier) {
        UserRole[] role = null;
        switch(accessQualifier){
            case "ADMIN":
                role = AdminAccess;
                break;
            case "USERADMIN":
                role = AdminUserAccess;
                break;
            case "ALL":
                role = AdminUserCustomerAccess;
                break;
            default:
                role = AdminUserCustomerAccess;
                break;
        }
        return role;
    }
}
