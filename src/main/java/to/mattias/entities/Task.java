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
@Table(name = "tasks")
public class Task extends Notable {

    private String taskTitle, taskDescription;
    private int taskEstimatedTimeH, taskEstimatedTimeM;
    private Date taskStartDate, taskEndDate;
    @OneToMany
    @Cascade({CascadeType.REFRESH, CascadeType.MERGE})
    private List<User> taskAssignedUsers;

    public Task() {
    }

    public Task(String taskTitle, String taskDescription, int taskEstimatedTimeH, int taskEstimatedTimeM, Date taskStartDate, Date taskEndDate, List<User> taskAssignedUsers) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskEstimatedTimeH = taskEstimatedTimeH;
        this.taskEstimatedTimeM = taskEstimatedTimeM;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.taskAssignedUsers = taskAssignedUsers;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskEstimatedTimeH() {
        return taskEstimatedTimeH;
    }

    public void setTaskEstimatedTimeH(int taskEstimatedTimeH) {
        this.taskEstimatedTimeH = taskEstimatedTimeH;
    }

    public int getTaskEstimatedTimeM() {
        return taskEstimatedTimeM;
    }

    public void setTaskEstimatedTimeM(int taskEstimatedTimeM) {
        this.taskEstimatedTimeM = taskEstimatedTimeM;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public List<User> getTaskAssignedUsers() {
        return taskAssignedUsers;
    }

    public void setTaskAssignedUsers(List<User> taskAssignedUsers) {
        this.taskAssignedUsers = taskAssignedUsers;
    }

    public void removeUser(User user) {
        if(getTaskAssignedUsers().contains(user)) {
            removeUser(user);
        }
    }
}
