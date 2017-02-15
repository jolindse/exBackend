package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;

/**
 * <h1>Created by Mattias on 2017-02-15.</h1>
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repo.findByUserName(s);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.getRoles());
    }
}
