package to.mattias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "projects")
public class Project implements Notable{

    @Id
    @GeneratedValue
    private Long projectId;
    private String projectTitle, projectDescription;
    private Customer projectCustomer;
    private List<Sprint> projectSprints;
    private List<Task> projectTasks;
    private List<User> projectUsers;

    public Project() {
    }

    public Project(String projectTitle, String description, Customer customer, List<Sprint> sprints, List<Task> tasks, List<User> users) {
        this.projectTitle = projectTitle;
        this.projectDescription = description;
        this.projectCustomer = customer;
        this.projectSprints = sprints;
        this.projectTasks = tasks;
        this.projectUsers = users;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
}
