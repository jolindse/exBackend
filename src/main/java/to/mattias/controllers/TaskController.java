package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Task;
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

    @GetMapping
    public List<Task> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Task save(@RequestBody Task task) {
        return service.save(task);
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    public Task findById(@PathVariable Long taskId) {
        return service.findById(taskId);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping
    @RequestMapping("/{taskId}")
    public void delete(@PathVariable Long taskId) {
        service.delete(taskId);
    }
}
