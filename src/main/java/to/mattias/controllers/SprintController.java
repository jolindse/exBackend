package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Sprint;
import to.mattias.services.ProjectService;
import to.mattias.services.SprintService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/sprint")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SprintController {

    @Autowired
    private SprintService service;
    @Autowired
    private ProjectService projectService;

    @DeleteMapping
    @RequestMapping(value = "/{projectId}/{sprintId}", method = RequestMethod.DELETE)
    @PreAuthorize("@securityService.hasRole(#projectId,'ADMIN')")
    public void removeSprint(@PathVariable int projectId,@PathVariable int sprintId) {
        service.removeSprint(sprintId);
    }

    @PostMapping
    @RequestMapping(value = "/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Sprint newSprint(@PathVariable int projectId, @RequestBody Sprint sprint) {
        projectService.findById(projectId).addSprint(sprint);
        return service.save(sprint);
    }

    @PutMapping(value = "/{projectId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Sprint updateSprint(@PathVariable int projectId,@RequestBody Sprint sprint) {
        return service.update(sprint);
    }

    @GetMapping
    @RequestMapping("/{projectId}/{sprintId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Sprint getById(@PathVariable int sprintId) {
        return service.findById(sprintId);
    }
}
