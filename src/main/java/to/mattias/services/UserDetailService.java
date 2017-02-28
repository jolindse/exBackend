package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;
import to.mattias.security.jwt.AuthenticatedUser;

/**
 * Created by juan on 2017-02-28.
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(s);
        return new AuthenticatedUser(user);
    }
}
