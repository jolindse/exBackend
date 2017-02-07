package to.mattias;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;
import to.mattias.repositories.SprintRepository;

import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Johan Lindström (jolindse@hotmail.com) on 2017-02-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional

public class SprintTest {

	@Autowired
	private SprintRepository repo;

	private Sprint defaultSprint;

	@Before
	public void initSprintTest() {
			defaultSprint = new Sprint();
			defaultSprint.setSprintTitle("TestSprint");
	}

	@Test
	public void testSaveSprint() {
		repo.save(defaultSprint);
		assertNotNull("Could not retrieve sprint from database", repo.findBySprintTitle("TestSprint"));
	}

	@Test
	public void testChangeTitleSprint() {

		Sprint currSprint = saveAndLoadDefault();

		currSprint.setSprintTitle("ChangedTitle");
		repo.save(currSprint);
		assertNotNull("Couldn't find the sprint with the changed title", repo.findBySprintTitle("ChangedTitle"));
	}

	@Test
	public void testSetSprintTaskDate() {

		Sprint currSprint = saveAndLoadDefault();

		currSprint.setSprintStartDate("2017-02-06");
		currSprint.setSprintEndDate("2017-02-07");

		repo.save(currSprint);
		currSprint = repo.findBySprintTitle("TestSprint");
		assertNotNull("Unexpected null response from start date",currSprint.getSprintStartDate());
		assertNotNull("Unexpected null repsones from end date",currSprint.getSprintEndDate());
	}

	@Test
	public void testCorrectDate() {
		Sprint currSprint = saveAndLoadDefault();
		currSprint.setSprintStartDate("2017-02-06");
		currSprint.setSprintEndDate("2017-02-07");
		Date startDate = currSprint.getSprintStartDate();
		Date endDate = currSprint.getSprintEndDate();
		repo.save(currSprint);

		currSprint = repo.findBySprintTitle("TestSprint");
		assertTrue("Start dates doesn't match",currSprint.getSprintStartDate().equals(startDate));
		assertTrue("End date doesn't match", currSprint.getSprintEndDate().equals(endDate));
	}


	@Test
	public void testAddTaskToSprint() {
		Sprint currSprint = saveAndLoadDefault();

		Task testTask = new Task();
		testTask.setTaskTitle("TestTask");
		currSprint.addTask(testTask);

		repo.save(currSprint);
		List<Task> sprintTasks = repo.findBySprintTitle("TestSprint").getSprintTasks();

		assertTrue("Task added could not be retrieved", sprintTasks.contains(testTask));
	}

	@Test
	public void testRemoveTaskFromSprint() {
		Sprint currSprint = saveAndLoadDefault();

		Task testTask = new Task();
		testTask.setTaskTitle("TestTask");
		currSprint.addTask(testTask);
		repo.save(currSprint);

		currSprint = repo.findBySprintTitle("TestSprint");
		currSprint.removeTask(testTask);
		repo.save(currSprint);

		currSprint = repo.findBySprintTitle("TestSprint");
		assertFalse("Task wasn't properly removed from sprint", currSprint.getSprintTasks().contains(testTask));
	}

	private Sprint saveAndLoadDefault() {
		repo.save(defaultSprint);
		return repo.findBySprintTitle("TestSprint");
	}
}
