package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import to.mattias.entities.Role;
import to.mattias.constants.UserAction;
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
        Role roleAdmin = new Role();
        Role roleUser = new Role();
        roleAdmin.setName("ADMIN");
        roleUser.setName("USER");

        User user1 = new User();
        user1.setUserFirstName("Anders");
        user1.setUserSurName("Testsson");
        user1.setAdmin(true);
        user1.setPassword("password");
        user1.setUserName("user1");
        user1.addRole(roleAdmin);

        User user2 = new User();
        user2.setUserFirstName("Lisa");
        user2.setUserSurName("Testlind");
        user2.setAdmin(false);
        user2.setPassword("password");
        user2.setUserName("user2");
        user2.addRole(roleUser);

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

        System.out.println("Curr project id: "+project1.getId());

        user2.addRightsForProject(project1.getId(), UserAction.READ);
        user2.addRightsForProject(project1.getId(), UserAction.CREATE);
        user2.addRightsForProject(project1.getId(), UserAction.UPDATE);

        System.out.println("Project id list: "+user2.getUserProjectsIds());

        userRepo.save(user1);
        userRepo.save(user2);

        return "ok";
    }


}
