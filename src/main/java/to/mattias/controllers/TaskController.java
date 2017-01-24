package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Task;
import to.mattias.services.ProjectService;
import to.mattias.services.TaskService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Task> findAll() {
        return service.findAll();
    }

    @PostMapping
    @RequestMapping("/{projectId}")
    public Task save(@PathVariable int projectId, @RequestBody Task task) {
        Task newTask = service.save(task);
        projectService.addTask(projectId, newTask);
        return newTask;
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    public Task findById(@PathVariable int taskId) {
        return service.findById(taskId);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping
    @RequestMapping("/{taskId}")
    public void delete(@PathVariable int taskId) {
        service.delete(taskId);
    }
}
