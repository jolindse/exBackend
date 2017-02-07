package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Cascade({CascadeType.ALL})
    private List<User> taskAssignedUsers = new ArrayList<>();

    @Transient
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

    public void setTaskStartDate(String dateString) {
        try {
            this.taskStartDate = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(String dateString) {
        try {
            this.taskEndDate = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<User> getTaskAssignedUsers() {
        return taskAssignedUsers;
    }

    public void setTaskAssignedUsers(List<User> taskAssignedUsers) {
        this.taskAssignedUsers = taskAssignedUsers;
    }

    public void removeUser(User user) {
        if(taskAssignedUsers.contains(user)) {
            taskAssignedUsers.remove(user);
        }
    }

    public void assignUserToTask(User user) {
        this.taskAssignedUsers.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (getTaskEstimatedTimeH() != task.getTaskEstimatedTimeH()) return false;
        if (getTaskEstimatedTimeM() != task.getTaskEstimatedTimeM()) return false;
        if (getTaskTitle() != null ? !getTaskTitle().equals(task.getTaskTitle()) : task.getTaskTitle() != null)
            return false;
        if (getTaskDescription() != null ? !getTaskDescription().equals(task.getTaskDescription()) : task.getTaskDescription() != null)
            return false;
        if (getTaskStartDate() != null ? !getTaskStartDate().equals(task.getTaskStartDate()) : task.getTaskStartDate() != null)
            return false;
        if (getTaskEndDate() != null ? !getTaskEndDate().equals(task.getTaskEndDate()) : task.getTaskEndDate() != null)
            return false;
        return getTaskAssignedUsers() != null ? getTaskAssignedUsers().equals(task.getTaskAssignedUsers()) : task.getTaskAssignedUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getTaskTitle() != null ? getTaskTitle().hashCode() : 0;
        result = 31 * result + (getTaskDescription() != null ? getTaskDescription().hashCode() : 0);
        result = 31 * result + getTaskEstimatedTimeH();
        result = 31 * result + getTaskEstimatedTimeM();
        result = 31 * result + (getTaskStartDate() != null ? getTaskStartDate().hashCode() : 0);
        result = 31 * result + (getTaskEndDate() != null ? getTaskEndDate().hashCode() : 0);
        result = 31 * result + (getTaskAssignedUsers() != null ? getTaskAssignedUsers().hashCode() : 0);
        return result;
    }
}
