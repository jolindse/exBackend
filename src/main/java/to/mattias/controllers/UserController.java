package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<User> getAll() {
        return service.findAll();
    }
}
