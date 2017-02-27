package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Project;
import to.mattias.services.ProjectService;
import to.mattias.services.SecurityService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public List<Project> findAll() {
        return service.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return service.save(project);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public Project findById(@PathVariable int projectId) {
        if (securityService.hasAuthority(projectId, this.getUsername())) {
            return service.findById(projectId);
        }
        return null;
    }

    @PutMapping
    public Project update(@RequestBody Project project) {
        if (securityService.hasAuthority(project.getId(), this.getUsername())) {
            return service.update(project);
        }
        return null;
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{projectId}")
    public void delete(@PathVariable int projectId) {
        service.delete(projectId);
    }

    private String getUsername() {
        User currUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currUser.getUsername();
    }
}
