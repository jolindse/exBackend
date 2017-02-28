package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import to.mattias.constants.UserAction;

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

    @ElementCollection(targetClass = UserAction.class)
    @Cascade(CascadeType.ALL)
    private List<UserAction> allowedActions = new ArrayList<>();

    public UserRights() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAction(UserAction action) {
        if (!allowedActions.contains(action)) {
            allowedActions.add(action);
        }
    }

    public void removeAction(UserAction action) {
        if(allowedActions.contains(action)) {
            allowedActions.remove(action);
        }
    }

    public List<UserAction> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(List<UserAction> allowedActions) {
        this.allowedActions = allowedActions;
    }
}
