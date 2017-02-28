package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import to.mattias.constants.UserAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */

@Entity
@Table(name = "users")
public class User extends Notable {

    private String userFirstName, userSurName, userName, password, email, phone;
    private Date userCreationDate;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Map<Integer, UserRights> roles = new HashMap<>();

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Role> roleList = new ArrayList<>();

    private boolean admin = false;

    public User() {
    }

    public User(String firstName, String surName, String userName, String password, String email, String phone, Date creationDate) {
        this.userFirstName = firstName;
        this.userSurName = surName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.userCreationDate = creationDate;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public List<Role> getRoles() {
        return this.roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        this.roleList.add(role);
    }

    public void removeRole(Role role) {
        this.roleList.remove(role);
    }

    public void addRightsForProject(int projId, UserAction action) {
        if (roles.containsKey(projId)) {
            UserRights currRights = roles.get(projId);
            currRights.addAction(action);
        } else {
            roles.put(projId, new UserRights());
            UserRights currRights = roles.get(projId);
            currRights.addAction(action);
        }
    }

    public void removeRightsFromProject(int projId, UserAction action) {
        if (roles.containsKey(projId)) {
            UserRights currRights = roles.get(projId);
            currRights.removeAction(action);
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setId(int id) {
        super.setId(id);
    }

    public int getId() {
        return super.getId();
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUserCreationDate() {
        return userCreationDate;
    }

    public void setUserCreationDate(Date userCreationDate) {
        this.userCreationDate = userCreationDate;
    }

    public boolean isAuthorized(int projId, UserAction userAction) {
        if (this.admin ) {
          return true;
        } else {
            if (roles.containsKey(projId)) {
                UserRights currRoles = roles.get(projId);
                if (currRoles.getAllowedActions().contains(userAction)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public List<Integer> getUserProjectsIds() {
        if (this.admin) {
            List currList = new ArrayList<>();
            currList.add(-1);
            return currList;
        }
        List<Integer> projIds = new ArrayList<>();
        for (Integer key: roles.keySet()) {
            projIds.add(key);
        }
        return projIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userFirstName != null ? !userFirstName.equals(user.userFirstName) : user.userFirstName != null)
            return false;
        if (userSurName != null ? !userSurName.equals(user.userSurName) : user.userSurName != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (userCreationDate != null ? !userCreationDate.equals(user.userCreationDate) : user.userCreationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userFirstName != null ? userFirstName.hashCode() : 0;
        result = 31 * result + (userSurName != null ? userSurName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userCreationDate != null ? userCreationDate.hashCode() : 0);
        return result;
    }
}
