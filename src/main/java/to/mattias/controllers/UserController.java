package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.User;
import to.mattias.services.UserService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/user")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public List<User> findAll() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public User createNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public User findById(@PathVariable int userId) {
        return service.findById(userId);
    }

    @PutMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping
    @RequestMapping("/{userId}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public void delete(@PathVariable int userId) {
        service.delete(userId);
    }
}
