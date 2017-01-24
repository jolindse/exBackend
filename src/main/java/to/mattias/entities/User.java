package to.mattias.entities;

import to.mattias.constants.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "users")
public class User extends Notable {

    private String userFirstName, userSurName, userName, password, email, phone;
    private Date userCreationDate;
    private Role role;

    public User() {
    }

    public User(String firstName, String surName, String userName, String password, String email, String phone, Date creationDate, Role role) {
        this.userFirstName = firstName;
        this.userSurName = surName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.userCreationDate = creationDate;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
