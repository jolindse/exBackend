package to.mattias.services;

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

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userService.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException(s + " not found.");
        } else {
            return new AuthenticatedUser(user);
        }
    }
}
