package to.mattias.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import to.mattias.constants.UserRole;

import javax.persistence.*;
import java.util.*;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */

@Entity
@Table(name = "users")
public class User extends Notable implements GrantedAuthority {

    private String userFirstName, userSurName, username, password, email, phone;
    private Date userCreationDate;

    @ElementCollection(targetClass = UserRole.class)
    @Cascade(CascadeType.ALL)
    private Map<Integer, UserRole> projectRoles = new HashMap<>();

    private UserRole mainRole;

    public User() {
    }

    public User(String firstName, String surName, String username, String password, String email, String phone, Date creationDate) {
        this.userFirstName = firstName;
        this.userSurName = surName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.userCreationDate = creationDate;
    }

    public UserRole getMainRole() {
        return mainRole;
    }

    public void setMainRole(UserRole mainRole) {
        this.mainRole = mainRole;
    }

    public void setProjectRole(int projId, UserRole role) {
        projectRoles.put(projId, role);
    }

    public void removeProjectRole(int projId) {
        if (projectRoles.containsKey(projId)) {
            projectRoles.remove(projId);
        }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAuthorized(int projId, UserRole[] requestedRoles) {
        if (this.mainRole == UserRole.ADMIN) {
            return true;
        } else {
            if (projectRoles.containsKey(projId)) {
                UserRole currRole = projectRoles.get(projId);
                if (Arrays.asList(requestedRoles).contains(currRole)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public List<Integer> getUserProjectsIds() {
        if (this.mainRole == UserRole.ADMIN) {
            List<Integer> currList = new ArrayList<>();
            currList.add(-1);
            return currList;
        }
        List<Integer> projIds = new ArrayList<>();
        for (Integer key : projectRoles.keySet()) {
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
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
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
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userCreationDate != null ? userCreationDate.hashCode() : 0);
        return result;
    }


    @Override
    public String getAuthority() {
        return this.mainRole.toString();
    }
}
