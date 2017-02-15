package to.mattias.restTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Project;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h1>Created by Mattias on 2017-01-31.</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@Order()
public class ProjectEndpointsTest {
    private static String url;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        url = "http://localhost:" + port + "/project";
    }

    @Test
    public void testProjectGet() {
        // Test that the returned array isn't null
        assertThat(restTemplate.getForObject(url, Project[].class)).isNotNull();
    }

    @Test
    public void testToPostProject() {
        // Instantiate a new Project and set title
        Project currProject = new Project();
        currProject.setProjectTitle("TestProject");

        // Persist the project and check if the returned object has the same title
        Project returnProject = restTemplate.postForObject(url, currProject, Project.class);
        assertThat(returnProject.getProjectTitle()).asString().isEqualTo(currProject.getProjectTitle());
    }

    @Test
    public void testToGetSpecificProject() {
        // Instantiate a new Project and persist it
        Project currProject = new Project();
        Project returnProject = restTemplate.postForObject(url, currProject, Project.class);

        // Get the id for the returned object
        int idToTestFor = returnProject.getId();

        // Check if the returned oject's id is the same that was asked for
        assertThat(restTemplate.getForObject(url + "/" + idToTestFor, Project.class).getId()).isEqualTo(idToTestFor);
    }

    @Test
    public void testToUpdateProject() {
        // Instantiate a new Project and persist it
        Project currProject = new Project();
        Project returnProject = restTemplate.postForObject(url, currProject, Project.class);

        // Update the returned object's title and get the id
        returnProject.setProjectTitle("Updated Title");
        int projectId = returnProject.getId();

        // PUT the updated object
        restTemplate.put(url, returnProject);

        // Check if the returned object has the new title
        assertThat(restTemplate.getForObject(url + "/" + projectId, Project.class)
                .getProjectTitle()).asString().isEqualTo("Updated Title");
    }

    @Test
    public void TestToDeleteProject() {
        // Instantiate a new Project and persist it
        Project currProject = new Project();
        Project returnProject = restTemplate.postForObject(url, currProject, Project.class);

        // Get the returned objects id and count the number of objects in db
        int projectId = returnProject.getId();
        int numberOfProjectsInDb = restTemplate.getForObject(url, Project[].class).length;

        // DELETE the object and assert that the number of objects in db has decreased by one
        restTemplate.delete(url + "/" + projectId);
        assertThat(restTemplate.getForObject(url, Project[].class).length).isEqualTo(numberOfProjectsInDb - 1);
    }
}