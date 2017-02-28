package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Project;
import to.mattias.entities.User;
import to.mattias.repositories.UserRepository;
import to.mattias.security.jwt.AuthenticatedUser;
import to.mattias.services.ProjectService;
import to.mattias.services.SecurityService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/project")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
        public List<Project> findAll() {
        AuthenticatedUser user = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currUser = this.userRepository.findByUserName(user.getName());
        List<Integer> currIds = currUser.getUserProjectsIds();
        if (currIds.size() > 0 && currIds.get(0) == -1) {
            return service.findAll();
        } else if (currIds.size() > 0) {
            return service.findByIdList(currIds);
        }
        return null;
    }

    @PostMapping
    @PreAuthorize("@securityService.hasRole({'USER','ADMIN'})")
    public Project saveProject(@RequestBody Project project) {
        return service.save(project);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    @PreAuthorize("@securityService.hasAuthority(#projectId,UserAction.READ)")
            public Project findById(@PathVariable int projectId) {
        return service.findById(projectId);
    }

    @PutMapping(value="/{projectId}")
    @PreAuthorize("@securityService.hasAuthority(#projectId,UserAction.UPDATE)")
    public Project update(@RequestBody Project project) {
        return service.update(project);
    }

    @DeleteMapping
    @RequestMapping("/{projectId}")
    @PreAuthorize("@securityService.hasRole({'ADMIN'})")
    public void delete(@PathVariable int projectId) {
        service.delete(projectId);
    }

}
