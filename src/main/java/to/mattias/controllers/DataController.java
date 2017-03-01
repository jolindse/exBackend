package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.mattias.constants.UserRole;
import to.mattias.entities.Project;
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
    public String initData() {

        User user1 = new User();
        user1.setUserFirstName("Anders");
        user1.setUserSurName("Testsson");
        user1.setMainRole(UserRole.ADMIN);
        user1.setPassword("password");
        user1.setUserName("user1");

        User user2 = new User();
        user2.setUserFirstName("Lisa");
        user2.setUserSurName("Testlind");
        user2.setMainRole(UserRole.USER);
        user2.setPassword("password");
        user2.setUserName("user2");

        User user3 = new User();
        user3.setUserFirstName("Anna");
        user3.setUserSurName("Beställarson");
        user3.setMainRole(UserRole.CUSTOMER);
        user3.setPassword("password");
        user3.setUserName("user3");

        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        Task task4 = new Task();

        task1.setTaskTitle("Task 1");
        task2.setTaskTitle("Task 2");
        task3.setTaskTitle("Task 3");
        task4.setTaskTitle("Task 4");

        Project proj1 = new Project();
        proj1.setProjectTitle("Proj 1");
        Project proj2 = new Project();
        proj2.setProjectTitle("Proj 2");

        Project project1 = projRepo.save(proj1);
        Project project2 = projRepo.save(proj2);

        taskRepo.save(task1);
        taskRepo.save(task2);
        taskRepo.save(task3);
        taskRepo.save(task4);

        user2.setProjectRole(project2.getId(),UserRole.USER);
        user3.setProjectRole(project2.getId(),UserRole.CUSTOMER);

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);

        return "ok";
    }


}
