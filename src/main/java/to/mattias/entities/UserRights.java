package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import to.mattias.constants.UserRole;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 2017-02-28.
 */

@Entity
public class UserRights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(targetClass = UserRole.class)
    @Cascade(CascadeType.ALL)
    private List<UserRole> allowedActions = new ArrayList<>();

    public UserRights() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAction(UserRole action) {
        if (!allowedActions.contains(action)) {
            allowedActions.add(action);
        }
    }

    public void removeAction(UserRole action) {
        if(allowedActions.contains(action)) {
            allowedActions.remove(action);
        }
    }

    public List<UserRole> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(List<UserRole> allowedActions) {
        this.allowedActions = allowedActions;
    }
}
