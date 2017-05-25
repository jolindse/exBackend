package to.mattias;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import to.mattias.entities.Customer;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.entities.User;
import to.mattias.repositories.ProjectRepository;


import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * <h1>Created by Mattias on 2017-01-30.</h1>
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
// @Rollback(false)

public class ProjectTest {

    @Autowired
    private ProjectRepository projectRepository;

    private static boolean init = false;

    @Before
    public void initProjectTests() {
        //if (!init) {
            Project project = new Project();
            project.setProjectTitle("DefaultProject");
            project.setProjectDescription("Default description");
            projectRepository.save(project);
            init = true;
        //}
    }

    @Test
    public void testToAddAProject() {
        Project projectTest = new Project();
        projectTest.setProjectTitle("TestProjekt1");
        assertTrue("Could not persist Project", projectRepository.save(projectTest).equals(projectTest)) ;
    }

    @Test
    public void testToRetrieveProject() {
        assertTrue("Could not retrieve project", projectRepository.findAll().size() > 0);
    }

    @Test
    public void testChangeProjectTitle() {
        Project currProject = new Project();
        currProject.setProjectTitle("ChangeMe");
        projectRepository.save(currProject);

        Project changed = projectRepository.findByProjectTitle("ChangeMe");
        changed.setProjectTitle("Changed");
        projectRepository.save(changed);

        assertTrue("Project title not changed in persisted state", projectRepository.findByProjectTitle("Changed").getProjectTitle().equals("Changed"));
    }

    @Test
    public void testChangeProjectDescription() {
        Project currProject = new Project();
        currProject.setProjectDescription("ChangeMe");
        projectRepository.save(currProject);

        Project changed = projectRepository.findByProjectDescription("ChangeMe");
        changed.setProjectDescription("Changed");
        projectRepository.save(changed);

        assertTrue("Project description not changed in persisted state", projectRepository.findByProjectDescription("Changed").getProjectDescription().equals("Changed"));
    }

    @Test
    public void testToAddASprintToProject() {
        Sprint sprint = new Sprint();
        sprint.setSprintTitle("TestSprint");

        Project currProject = projectRepository.findByProjectTitle("DefaultProject");
        System.out.println(currProject);
        currProject.addSprint(sprint);

        projectRepository.save(currProject);


        assertTrue("Sprint added but not found",projectRepository.findByProjectTitle("DefaultProject")
                .getProjectSprints().contains(sprint));
    }

    @Test
    public void testRemoveSprintFromProject() {
        Sprint sprint = new Sprint();
        sprint.setSprintTitle("TestSprint");
        Project currProject = projectRepository.findByProjectTitle("DefaultProject");
        currProject.addSprint(sprint);
        projectRepository.save(currProject);

        Sprint currSprint = currProject.getProjectSprints().get(0);
        currProject.removeSprint(currSprint);
        projectRepository.save(currProject);

        assertFalse("Sprint not removed from persisted project", projectRepository.findByProjectTitle("DefaultProject").getProjectSprints().contains(currSprint));
    }

    @Test
    public void testAddUserToProject() {
        int userListSize = 0;

        Project currProject = projectRepository.findByProjectTitle("DefaultProject");
        userListSize = currProject.getProjectUsers().size();
        User testUser = new User();
        testUser.setUserFirstName("Mattias");

        currProject.addUser(testUser);
        projectRepository.save(currProject);

        assertTrue("User not added to project", projectRepository.findByProjectTitle("DefaultProject")
                .getProjectUsers().size() == userListSize+1);
    }

    @Test
    public void testRemoveUserFromProject() {
        // Create a new User
        User testUser = new User();
        testUser.setUserFirstName("Mattias");

        // Fetch a project from the db
        Project currProject = projectRepository.findByProjectTitle("DefaultProject");

        // Add the user to the project and persist the project
        currProject.addUser(testUser);
        projectRepository.save(currProject);

        // Assert that the user is in the project
        currProject = projectRepository.findByProjectTitle("DefaultProject");
        assertTrue(currProject.getProjectUsers().contains(testUser));

        // Remove the user from the project
        currProject.removeUser(testUser);
        projectRepository.save(currProject);
        assertFalse("User still assigned to project.",projectRepository.findByProjectTitle("DefaultProject").getProjectUsers().contains(testUser));
    }

    @Test
    public void testToAssignCustomerToProject() {
        // Create a new Customer
        Customer customer = new Customer();
        customer.setCustomerName("TestCustomer");

        // Fetch a project from db and add the customer
        Project currProject = projectRepository.findByProjectTitle("DefaultProject");
        currProject.setProjectCustomer(customer);

        // Persist the project and assert that the customer is contained in the project
        projectRepository.save(currProject);
        assertTrue("The project doesn't contain the customer",
                projectRepository.findByProjectTitle("DefaultProject").getProjectCustomer().equals(customer));
    }
}
