package to.mattias;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.constants.TaskStatus;
import to.mattias.entities.Task;
import to.mattias.entities.User;
import to.mattias.repositories.TaskRepository;

import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Johan Lindström (jolindse@hotmail.com) on 2017-02-07.
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class TaskTest {

	@Autowired
	TaskRepository repo;

	private Task defaultTask;

	@Before
	public void initSprintTest() {
			defaultTask = new Task();
			defaultTask.setTaskTitle("TestTask");
	}

	@Test
	public void testAddAndSaveTask() {
		repo.save(defaultTask);
		assertNotNull("Couldn't retrieve saved task",repo.findByTaskTitle("TestTask"));
	}

	@Test
	public void testChangeTaskInformation() {

		String changedTitle = "Changed";
		String changedDescription = "Changed description";
		int changedEstimatedTimeH = 1;
		int changedEstimatedTimeM = 20;

		defaultTask.setTaskTitle(changedTitle);
		defaultTask.setTaskDescription(changedDescription);
		defaultTask.setTaskEstimatedTimeH(changedEstimatedTimeH);
		defaultTask.setTaskEstimatedTimeM(changedEstimatedTimeM);

		repo.save(defaultTask);

		defaultTask = repo.findByTaskTitle(changedTitle);

		assertTrue("Title doesn't match", defaultTask.getTaskTitle().equals(changedTitle));
		assertTrue("Description doesn't match", defaultTask.getTaskDescription().equals(changedDescription));
		assertTrue("Estimated time H doesn't match", defaultTask.getTaskEstimatedTimeH() == changedEstimatedTimeH);
		assertTrue("Estimated time M doesn't match", defaultTask.getTaskEstimatedTimeM() == changedEstimatedTimeM);
	}

	@Test
	public void testSetTaskDates() {
		defaultTask.setTaskStartDate("2017-02-06");
		defaultTask.setTaskEndDate("2017-02-07");

		Date startDate = defaultTask.getTaskStartDate();
		Date endDate = defaultTask.getTaskEndDate();

		repo.save(defaultTask);
		defaultTask = repo.findByTaskTitle("TestTask");

		assertTrue("Start dates doesn't match", defaultTask.getTaskStartDate().equals(startDate));
		assertTrue("End dates doesn't match", defaultTask.getTaskEndDate().equals(endDate));
	}

	@Test
	public void testAssignUserToTask() {
		User testUser = new User();
		testUser.setUsername("Lars");

		defaultTask.assignUserToTask(testUser);
		repo.save(defaultTask);

		defaultTask = repo.findByTaskTitle("TestTask");
		assertTrue("User not properly added", defaultTask.getTaskAssignedUsers().contains(testUser));
	}

	@Test
	public void testRemoveAssignedUser() {
		User testUser = new User();
		testUser.setUsername("Lars");

		defaultTask.assignUserToTask(testUser);
		repo.save(defaultTask);

		defaultTask = repo.findByTaskTitle("TestTask");
		defaultTask.removeUser(testUser);
		assertFalse("Could not remove user", defaultTask.getTaskAssignedUsers().contains(testUser));
	}

	@Test
	public void testToChangeTaskStatus() {
		repo.save(defaultTask);
		Task currTask = repo.findByTaskTitle("TestTask");
		currTask.setTaskStatus(TaskStatus.WIP);
		Task returnedTask = repo.save(currTask);
		assertTrue("Couldn't change Task Status", returnedTask.getTaskStatus() == TaskStatus.WIP);
	}

}
