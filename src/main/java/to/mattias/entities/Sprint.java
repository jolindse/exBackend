package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "sprints")
public class Sprint extends Notable {
    private String sprintTitle;
    @OneToMany
    @Cascade({CascadeType.REFRESH, CascadeType.MERGE})
    private List<Task> sprintTasks;
    private Date sprintStartDate, sprintEndDate;

    public Sprint() {
    }

    public Sprint(String sprintTitle, List<Task> sprintTasks, Date sprintStartDate, Date endDate) {
        this.sprintTitle = sprintTitle;
        this.sprintTasks = sprintTasks;
        this.sprintStartDate = sprintStartDate;
        this.sprintEndDate = endDate;
    }

    public String getSprintTitle() {
        return sprintTitle;
    }

    public void setSprintTitle(String sprintTitle) {
        this.sprintTitle = sprintTitle;
    }

    public List<Task> getSprintTasks() {
        return sprintTasks;
    }

    public void setSprintTasks(List<Task> sprintTasks) {
        this.sprintTasks = sprintTasks;
    }

    public Date getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(Date sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public Date getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(Date sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    public void addTask(Task taskToAdd) {
        this.sprintTasks.add(taskToAdd);
    }

    public void removeTask(Task taskToRemove) {
        this.sprintTasks.remove(taskToRemove);
    }

    public void removeUserFromTasks(User user) {
        sprintTasks.forEach(task -> {
            task.removeUser(user);
        });
    }
}
