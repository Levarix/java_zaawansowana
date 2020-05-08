package pl.jazapp.app.webapp;

import pl.jazapp.app.webapp.login.LoginRequest;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@SessionScoped
@Named
public class UserContext implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean isLogged;
    private String fullName;

    @Inject
    UserRepository userRepository;

    public UserContext() {
    }

    public UserContext(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public boolean isLogged() {

        return isLogged;
    }

    public void login(LoginRequest loginRequest) {
        isLogged = true;
        User user = userRepository.findByUsername(loginRequest.getUsername()).get();
        setFullName(user.getFirstName() + " " + user.getLastName());
    }

    public void logout (){
        isLogged = false;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
