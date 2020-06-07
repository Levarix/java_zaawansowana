package pl.jazapp.app.webapp.users;

import pl.jazapp.app.webapp.login.LoginRequest;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;


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
        Optional<UserEntity> user = userRepository.findByUsername(loginRequest.getUsername());
        if(user.isPresent()){
            UserEntity existingUser = user.get();
            setFullName(existingUser.getFirst_name() + " " + existingUser.getLast_name());
        }

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
