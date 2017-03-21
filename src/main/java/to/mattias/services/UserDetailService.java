package to.mattias.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import to.mattias.entities.User;
import to.mattias.security.jwt.AuthenticatedUser;

/**
 * Created by juan on 2017-02-28.
 */
@Service
public class UserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger("kanban-logger");


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userService.findByUserName(s);
        if (user == null) {
            logger.error(String.format("Failed to login - Username %s not found", s));
            throw new UsernameNotFoundException(s + " not found.");
        } else {
            return new AuthenticatedUser(user);
        }
    }
}
