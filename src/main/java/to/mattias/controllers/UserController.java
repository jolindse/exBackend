package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.User;
import to.mattias.services.UserService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @PostMapping
    public User createNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User findById(@PathVariable Long userId) {
        return service.findById(userId);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping
    @RequestMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        service.delete(userId);
    }
}
