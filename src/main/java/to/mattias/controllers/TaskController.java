package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Task;
import to.mattias.services.ProjectService;
import to.mattias.services.SprintService;
import to.mattias.services.TaskService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/task")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Task> findAll() {
        return service.findAll();
    }

    @PostMapping
    @RequestMapping("/{projectId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Task save(@PathVariable int projectId, @RequestBody Task task) {
        Task newTask = service.save(task);
        projectService.addTask(projectId, newTask);
        return newTask;
    }

    @RequestMapping(value = "/{projectId}/{taskId}", method = RequestMethod.GET)
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Task findById(@PathVariable int projectId, @PathVariable int taskId) {
        return service.findById(taskId);
    }

    @PutMapping(value="/{projectId}")
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public Task update(@PathVariable int projectId, @RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping
    @RequestMapping(value = "/{projectId}/{taskId}", method = RequestMethod.DELETE)
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public void delete(@PathVariable int projectId, @PathVariable int taskId) {
        Task currTask = service.findById(taskId);
        projectService.removeTask(currTask);
        sprintService.removeTask(currTask);
        service.delete(taskId);
    }
}
