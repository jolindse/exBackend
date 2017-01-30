package to.mattias;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Project;
import to.mattias.entities.Sprint;
import to.mattias.repositories.ProjectRepository;

import static org.junit.Assert.assertTrue;


/**
 * <h1>Created by Mattias on 2017-01-30.</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testToAddAProject() {
        Project project = new Project();
        project.setProjectTitle("TestProjekt");
        assertTrue("Could not persist Project", projectRepository.save(project) != null) ;
    }

    @Test
    public void testToRetrieveProject() {
        assertTrue("Could not retrieve project", projectRepository.findAll().size() > 0);
    }

    @Test
    public void testToAddASprintToProject() {
        projectRepository.findByProjectTitle("TestProjekt").addSprint(new Sprint());
    }
}
