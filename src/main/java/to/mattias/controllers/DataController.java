package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import to.mattias.constants.TaskStatus;
import to.mattias.constants.UserRole;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;
import to.mattias.entities.User;
import to.mattias.repositories.ProjectRepository;
import to.mattias.repositories.TaskRepository;
import to.mattias.repositories.UserRepository;

/**
 * Created by juan on 2017-02-28.
 */

@RestController
@RequestMapping(value="/data")
public class DataController {

    @Autowired
    private ProjectRepository projRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;


    @GetMapping(value="/initdata")
    @ResponseStatus(HttpStatus.CREATED)
    public String initData() {

        User user1 = new User();
        user1.setUserFirstName("Anders");
        user1.setUserSurName("Testsson");
        user1.setMainRole(UserRole.ADMIN);
        user1.setPassword("password");
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUserFirstName("Lisa");
        user2.setUserSurName("Testlind");
        user2.setMainRole(UserRole.USER);
        user2.setPassword("password");
        user2.setUsername("user2");

        User user3 = new User();
        user3.setUserFirstName("Anna");
        user3.setUserSurName("Beställarson");
        user3.setMainRole(UserRole.CUSTOMER);
        user3.setPassword("password");
        user3.setUsername("user3");

        Project project1 = createProject1();
        Project project2 = createProject2();

        user2.setProjectRole(project1.getId(),UserRole.USER);
        user3.setProjectRole(project1.getId(),UserRole.CUSTOMER);

        user2.setProjectRole(project2.getId(), UserRole.CUSTOMER);
        user3.setProjectRole(project2.getId(), UserRole.USER);

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);

        return "ok";
    }

    private Project createProject1() {
        Project project = new Project();
        project.setProjectTitle("Project 1");
        project.setProjectDescription("Description for Project 1");

        Sprint sprint1 = new Sprint();
        project.addSprint(sprint1);

        Task task1 = new Task();
        task1.setTaskTitle("Task 1");
        task1.setTaskDescription("Description for Task 1");
        task1.setTaskStatus(TaskStatus.TODO);
        sprint1.addTask(task1);
        taskRepo.save(task1);

        Task task2 = new Task();
        task2.setTaskTitle("Task 2");
        task2.setTaskDescription("Description for Task 2");
        task2.setTaskStatus(TaskStatus.WIP);
        sprint1.addTask(task2);
        taskRepo.save(task2);

        Task task3 = new Task();
        task3.setTaskTitle("Task 3");
        task3.setTaskDescription("Description for Task 3");
        task3.setTaskStatus(TaskStatus.TEST);
        sprint1.addTask(task3);
        taskRepo.save(task3);

        Task task4 = new Task();
        task4.setTaskTitle("Task 4");
        task4.setTaskDescription("Description for Task 4");
        task4.setTaskStatus(TaskStatus.DONE);
        sprint1.addTask(task4);
        taskRepo.save(task4);

        Task task5 = new Task();
        task5.setTaskTitle("Task 5");
        task5.setTaskDescription("Description for Task 5");
        task5.setTaskStatus(TaskStatus.TEST);
        sprint1.addTask(task5);
        taskRepo.save(task5);

        Task task6 = new Task();
        task6.setTaskTitle("Task 6");
        task6.setTaskDescription("Description for Task 6");
        task6.setTaskStatus(TaskStatus.WIP);
        sprint1.addTask(task6);
        taskRepo.save(task6);

        Task task7 = new Task();
        task7.setTaskTitle("Task 7");
        task7.setTaskDescription("Description for Task 7");
        task7.setTaskStatus(TaskStatus.TODO);
        sprint1.addTask(task7);
        taskRepo.save(task7);

        Task task8 = new Task();
        task8.setTaskTitle("Task 8");
        task8.setTaskDescription("Description for Task 8");
        task8.setTaskStatus(TaskStatus.WIP);
        sprint1.addTask(task8);
        taskRepo.save(task8);

        return projRepo.save(project);
    }

    private Project createProject2() {
        Project project = new Project();
        project.setProjectTitle("Project 2");
        project.setProjectDescription("Description for Project 2");

        Sprint sprint2 = new Sprint();
        project.addSprint(sprint2);

        Task task9 = new Task();
        task9.setTaskTitle("Task 9");
        task9.setTaskDescription("Description for Task 9");
        task9.setTaskStatus(TaskStatus.TODO);
        sprint2.addTask(task9);
        taskRepo.save(task9);

        Task task10 = new Task();
        task10.setTaskTitle("Task 10");
        task10.setTaskDescription("Description for Task 10");
        task10.setTaskStatus(TaskStatus.WIP);
        sprint2.addTask(task10);
        taskRepo.save(task10);

        Task task11 = new Task();
        task11.setTaskTitle("Task 11");
        task11.setTaskDescription("Description for Task 11");
        task11.setTaskStatus(TaskStatus.TEST);
        sprint2.addTask(task11);
        taskRepo.save(task11);

        Task task12 = new Task();
        task12.setTaskTitle("Task 12");
        task12.setTaskDescription("Description for Task 12");
        task12.setTaskStatus(TaskStatus.DONE);
        sprint2.addTask(task12);
        taskRepo.save(task12);

        Task task13 = new Task();
        task13.setTaskTitle("Task 13");
        task13.setTaskDescription("Description for Task 13");
        task13.setTaskStatus(TaskStatus.TEST);
        sprint2.addTask(task13);
        taskRepo.save(task13);

        Task task14 = new Task();
        task14.setTaskTitle("Task 14");
        task14.setTaskDescription("Description for Task 14");
        task14.setTaskStatus(TaskStatus.WIP);
        sprint2.addTask(task14);
        taskRepo.save(task14);

        Task task15 = new Task();
        task15.setTaskTitle("Task 15");
        task15.setTaskDescription("Description for Task 15");
        task15.setTaskStatus(TaskStatus.TODO);
        sprint2.addTask(task15);
        taskRepo.save(task15);

        Task task16 = new Task();
        task16.setTaskTitle("Task 16");
        task16.setTaskDescription("Description for Task 16");
        task16.setTaskStatus(TaskStatus.WIP);
        sprint2.addTask(task16);
        taskRepo.save(task16);

        return projRepo.save(project);
    }
}
