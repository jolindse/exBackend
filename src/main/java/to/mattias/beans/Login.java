package to.mattias.beans;
import to.mattias.entities.User;

/**
 * Created by juan on 2017-03-01.
 */
public class Login {

    private String token;
    private User user;

    public Login(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
