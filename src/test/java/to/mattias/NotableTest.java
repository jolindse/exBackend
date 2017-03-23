package to.mattias;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.entities.User;
import to.mattias.repositories.NotableRepository;
import to.mattias.services.ProjectService;
import to.mattias.services.UserService;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by mattias on 2017-03-23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class NotableTest {

    @Autowired
    private UserService userService;
    @Autowired
    private NotableRepository notableRepository;
    @Autowired
    private ProjectService projectService;

    @Test
    public void testToFindUser() {
        User user = new User();
        user.setUserFirstName("Mattias");

        int userId = userService.saveUser(user).getId();
        assertTrue("Couldn't find user",
                notableRepository.findOne(userId).equals(user));
    }

    @Test
    public void testToFindProject() {
        Project proj = new Project();
        proj.setProjectTitle("TestProjekt");
        int projectId = projectService.save(proj).getId();

        assertTrue("Couldn't find project",
                notableRepository.findOne(projectId).equals(proj));
    }

    @Test
    public void testToPersistArbitraryNotable() {
        Project proj = new Project();
        proj.setProjectTitle("TJOLAHOPP");

        int id = notableRepository.save(proj).getId();
        assertTrue("Doesn't work", projectService.findById(id).equals(proj));
    }
}
