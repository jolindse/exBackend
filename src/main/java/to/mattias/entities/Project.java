package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "projects")
public class Project extends Notable{

    private String projectTitle, projectDescription;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Customer projectCustomer;

    @OneToMany//(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Sprint> projectSprints;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Task> projectTasks;

    @OneToMany//(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<User> projectUsers;

    public Project() {
        super();
        this.projectSprints = new ArrayList<>();
        this.projectTasks = new ArrayList<>();
        this.projectUsers = new ArrayList<>();
    }

    public Project(String projectTitle, String description, Customer customer, List<Sprint> sprints, List<Task> tasks, List<User> users) {
        super();
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String description) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (getProjectTitle() != null ? !getProjectTitle().equals(project.getProjectTitle()) : project.getProjectTitle() != null)
            return false;
        if (getProjectDescription() != null ? !getProjectDescription().equals(project.getProjectDescription()) : project.getProjectDescription() != null)
            return false;
        if (getProjectCustomer() != null ? !getProjectCustomer().equals(project.getProjectCustomer()) : project.getProjectCustomer() != null)
            return false;
        if (getProjectSprints() != null ? !getProjectSprints().equals(project.getProjectSprints()) : project.getProjectSprints() != null)
            return false;
        if (getProjectTasks() != null ? !getProjectTasks().equals(project.getProjectTasks()) : project.getProjectTasks() != null)
            return false;
        return getProjectUsers() != null ? getProjectUsers().equals(project.getProjectUsers()) : project.getProjectUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getProjectTitle() != null ? getProjectTitle().hashCode() : 0;
        result = 31 * result + (getProjectDescription() != null ? getProjectDescription().hashCode() : 0);
        result = 31 * result + (getProjectCustomer() != null ? getProjectCustomer().hashCode() : 0);
        result = 31 * result + (getProjectSprints() != null ? getProjectSprints().hashCode() : 0);
        result = 31 * result + (getProjectTasks() != null ? getProjectTasks().hashCode() : 0);
        result = 31 * result + (getProjectUsers() != null ? getProjectUsers().hashCode() : 0);
        return result;
    }
}
