package pl.jazapp.app.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.UserContext;
import pl.jazapp.app.webapp.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Optional;


@RequestScoped
public class LoginService {
    @Inject
    UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    UserContext userContext;

    public LoginService() {
        this(new UserRepository());
    }

    public LoginService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean login(LoginRequest loginRequest) {

        Optional<User> checkingUser = userRepository.findByUsername(loginRequest.getUsername());

        if (checkingUser.isPresent()) {
            User existingCheckingUser = checkingUser.get();
            if (existingCheckingUser.getPassword().equals(loginRequest.getPassword())) {
                userContext.login(loginRequest);
                logger.warn(String.format("Logged successfully with username: %s, and password: %s", loginRequest.getUsername(), loginRequest.getPassword()));
                return true;
            } else {
                logger.warn(String
                        .format("Wrong password %s %s",
                                loginRequest.getUsername(),
                                loginRequest.getPassword()));

                FacesContext.getCurrentInstance().getExternalContext().getFlash()
                        .put("error-message", "Wrong username or password");
            }
        } else {
            logger.warn(String
                    .format("Wrong username %s %s",
                    loginRequest.getUsername(),
                    loginRequest.getPassword()));

            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Wrong username or password");
        }
        return false;
    }

    void logout () {
        userContext.logout();
    }
}
