package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.mattias.beans.Cmd;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;
import to.mattias.services.ProjectService;
import to.mattias.services.SprintService;
import to.mattias.services.TaskService;
import to.mattias.services.UserService;

/**
 * <h1>Created by Mattias on 2017-01-24.</h1>
 */
@RestController
@RequestMapping("/cmd")
public class CommandController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SprintService sprintService;
    @Autowired
    private UserService userService;

    private Project project;
    private Sprint sprint;
    private Task task;

    @PostMapping
    public void handleCommand(@RequestBody Cmd currCommand) {
        int projectId, sprintId, taskId, userId;

        switch(currCommand.getCmd()) {

            case "assignUserToProject":
                projectId = (int)currCommand.getPayload()[0];
                userId = (int)currCommand.getPayload()[1];

                project = projectService.findById(projectId);
                project.addUser(userService.findById(userId));

                projectService.save(project);
                break;

            case "removeUserFromProject":
                projectId = (int)currCommand.getPayload()[0];
                userId = (int)currCommand.getPayload()[1];

                project = projectService.findById(projectId);
                project.removeUser(userService.findById(userId));

                projectService.save(project);
                break;

            case "addTaskToSprint":
                projectId = (int)currCommand.getPayload()[0];
                sprintId = (int)currCommand.getPayload()[1];
                taskId = (int)currCommand.getPayload()[2];

                getProjSprintTask(projectId, sprintId, taskId);

                project.removeTask(task);
                sprint.addTask(task);

                projectService.save(project);
                sprintService.save(sprint);
                break;

            case "removeTaskFromSprint":
                projectId = (int)currCommand.getPayload()[0];
                sprintId = (int)currCommand.getPayload()[1];
                taskId = (int)currCommand.getPayload()[2];

                getProjSprintTask(projectId, sprintId, taskId);

                sprint.removeTask(task);
                project.addTask(task);

                projectService.save(project);
                sprintService.save(sprint);
                break;

            case "moveTaskFromSprintToSprint":
                int sprintFromId = (int)currCommand.getPayload()[0];
                int sprintToId = (int)currCommand.getPayload()[1];
                taskId = (int)currCommand.getPayload()[2];

                Sprint fromSprint = sprintService.findById(sprintFromId);
                Sprint toSprint = sprintService.findById(sprintToId);
                task = taskService.findById(taskId);

                fromSprint.removeTask(task);
                toSprint.addTask(task);

                sprintService.save(fromSprint);
                sprintService.save(toSprint);
                break;

            case "assignUserToTask":
                task = taskService.findById((int)currCommand.getPayload()[0]);
                task.assignUserToTask(userService.findById((int)currCommand.getPayload()[1]));
                taskService.save(task);
                break;

            case "removeUserFromTask":
                task = taskService.findById((int)currCommand.getPayload()[0]);
                task.removeUser(userService.findById((int)currCommand.getPayload()[1]));
                taskService.save(task);
                break;
        }

    }

    private void getProjSprintTask(int projectId, int sprintId, int taskId) {
        task = taskService.findById(taskId);
        project = projectService.findById(projectId);
        sprint = sprintService.findById(sprintId);
    }
}
