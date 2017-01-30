package to.mattias;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Project;
import to.mattias.entities.Task;
import to.mattias.repositories.ProjectRepository;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * <h1>Created by Mattias on 2017-01-30.</h1>
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectTest {

    @Autowired
    private ProjectRepository repo;

    @Test
    public void testToAddAProject() {
        Project project = new Project();
        project.setProjectTitle("TestProjekt");
        assertTrue("Could not persist Project", repo.save(project) != null) ;
        assertTrue("Could not retrieve projects", repo.findAll().size() > 0);
    }

}
