package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Project;
import to.mattias.services.ProjectService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public List<Project> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return service.save(project);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public Project findById(@PathVariable int projectId) {
        return service.findById(projectId);
    }

    @PutMapping
    public Project update(@RequestBody Project project) {
        return service.update(project);
    }

    @DeleteMapping
    @RequestMapping("/{projectId}")
    public void delete(@PathVariable int projectId) {
        service.delete(projectId);
    }
}
