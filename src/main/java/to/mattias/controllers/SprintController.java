package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Sprint;
import to.mattias.services.ProjectService;
import to.mattias.services.SprintService;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private SprintService service;
    @Autowired
    private ProjectService projectService;

    @DeleteMapping
    @RequestMapping(value = "/{sprintId}", method = RequestMethod.DELETE)
    public void removeSprint(@PathVariable int sprintId) {
        service.removeSprint(sprintId);
    }

    @PostMapping
    @RequestMapping(value = "/{projectId}", method = RequestMethod.POST)
    public Sprint newSprint(@PathVariable int projectId, @RequestBody Sprint sprint) {
        projectService.findById(projectId).addSprint(sprint);
        return service.save(sprint);
    }

    @PutMapping
    public Sprint updateSprint(@RequestBody Sprint sprint) {
        return service.update(sprint);
    }

    @GetMapping
    @RequestMapping("/{sprintId}")
    public Sprint getById(@PathVariable int sprintId) {
        return service.findById(sprintId);
    }

}
