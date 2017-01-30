package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.mattias.entities.Sprint;
import to.mattias.entities.Task;
import to.mattias.repositories.ProjectRepository;
import to.mattias.repositories.SprintRepository;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class SprintService {

    @Autowired
    private SprintRepository repo;

    @Autowired
    private ProjectService projectService;

    public Sprint findById(int sprintId) {
        return repo.findOne(sprintId);
    }

    public Sprint save(Sprint sprintToSave) {
        return repo.save(sprintToSave);
    }

    public void removeTask (Task task) {
        Sprint currSprint = repo.findBySprintTasks(task);
        if (currSprint != null) {
            currSprint.removeTask(task);
            save(currSprint);
        }
    }

    public void removeSprint (int sprintId) {
        Sprint currSprint = repo.findOne(sprintId);
        int projectId = projectService.getProjectIdForSprint(currSprint);
        projectService.removeSprintFromProject(projectId, currSprint);
        currSprint.getSprintTasks().forEach(currTask ->{
            currSprint.removeTask(currTask);
            projectService.addTask(projectId, currTask);
        });
        repo.delete(sprintId);
    }

    public Sprint update(Sprint sprint) {
        return save(sprint);
    }

}
