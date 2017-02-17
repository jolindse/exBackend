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
@Table(name = "sprints")
public class Sprint extends Notable {
    private String sprintTitle;
    @OneToMany
    @Cascade({CascadeType.ALL})
    private List<Task> sprintTasks = new ArrayList<>();
    private Date sprintStartDate, sprintEndDate;

    @Transient
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Sprint() {
        super();
    }

    public Sprint(String sprintTitle, List<Task> sprintTasks, Date sprintStartDate, Date endDate) {
        super();
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

    public void setSprintStartDate(String dateString) {
        try {
            this.sprintStartDate = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(String dateString) {
        try {
            this.sprintEndDate = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;

        Sprint sprint = (Sprint) o;

        if (sprintTitle != null ? !sprintTitle.equals(sprint.sprintTitle) : sprint.sprintTitle != null) return false;
        if (sprintTasks != null ? !sprintTasks.equals(sprint.sprintTasks) : sprint.sprintTasks != null) return false;
        if (sprintStartDate != null ? !sprintStartDate.equals(sprint.sprintStartDate) : sprint.sprintStartDate != null)
            return false;
        return sprintEndDate != null ? sprintEndDate.equals(sprint.sprintEndDate) : sprint.sprintEndDate == null;
    }

    @Override
    public int hashCode() {
        int result = sprintTitle != null ? sprintTitle.hashCode() : 0;
        result = 31 * result + (sprintTasks != null ? sprintTasks.hashCode() : 0);
        result = 31 * result + (sprintStartDate != null ? sprintStartDate.hashCode() : 0);
        result = 31 * result + (sprintEndDate != null ? sprintEndDate.hashCode() : 0);
        return result;
    }
}
