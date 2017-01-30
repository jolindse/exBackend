package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "projects")
public class Project extends Notable{

    private String projectTitle, projectDescription;
    @OneToOne
    @Cascade(CascadeType.REFRESH)
    private Customer projectCustomer;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Sprint> projectSprints;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Task> projectTasks;
    @OneToMany
    @Cascade(CascadeType.REFRESH)
    private List<User> projectUsers;

    public Project() { }

    public Project(String projectTitle, String description, Customer customer, List<Sprint> sprints, List<Task> tasks, List<User> users) {
        this.projectTitle = projectTitle;
        this.projectDescription = description;
        this.projectCustomer = customer;
        this.projectSprints = sprints;
        this.projectTasks = tasks;
        this.projectUsers = users;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return projectDescription;
    }

    public void setDescription(String description) {
        this.projectDescription = description;
    }

    public Customer getProjectCustomer() {
        return projectCustomer;
    }

    public void setProjectCustomer(Customer projectCustomer) {
        this.projectCustomer = projectCustomer;
    }

    public List<Sprint> getProjectSprints() {
        return projectSprints;
    }

    public void setProjectSprints(List<Sprint> projectSprints) {
        this.projectSprints = projectSprints;
    }

    public List<Task> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<Task> projectTasks) {
        this.projectTasks = projectTasks;
    }

    public List<User> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(List<User> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public void addTask(Task task) {
        this.projectTasks.add(task);
    }

    public void removeTask(Task taskToRemove) {
        this.projectTasks.remove(taskToRemove);
    }

    public void addUser(User user) {
        this.projectUsers.add(user);
    }

    public void removeUser(User user) {
        if(projectUsers.contains(user)) {
            projectUsers.remove(user);
        }
        projectTasks.forEach(task -> {
            task.removeUser(user);
        });
        projectSprints.forEach(sprint -> {
            sprint.removeUserFromTasks(user);
        });
    }

    public void removeSprint(Sprint sprint) {
        projectSprints.remove(sprint);
    }

    public void addSprint(Sprint sprint) {
        projectSprints.add(sprint);
    }
}
