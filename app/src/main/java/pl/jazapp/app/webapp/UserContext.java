package pl.jazapp.app.webapp;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class UserContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean isLogged;

    public UserContext() {
    }

    public UserContext(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void login() {
        isLogged = true;
    }
}
