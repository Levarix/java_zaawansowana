package pl.jazapp.app.webapp.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.UserRepository;
import pl.jazapp.app.webapp.login.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
public class RegisterService {

    @Inject
    UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public RegisterService() {
        this(new UserRepository());
    }

    public RegisterService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean register (RegisterRequest registerRequest) {

        if (registerRequest.getPassword().equals(registerRequest.getPasswordCheck())) {
            Optional<User> checkingUser = userRepository.findByUsername(registerRequest.getUsername());

            if (!checkingUser.isPresent()) {
                userRepository.createUser(registerRequest);
                return true;
            } else {
                logger.warn(String.format("Username already exists: %s", registerRequest.getUsername()));
                FacesContext.getCurrentInstance().getExternalContext().getFlash()
                        .put("error-message", "Username is already taken");
            }
        } else {
            logger.warn(String.format("Passwords do not match"));

            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Passwords do not match");
        }

        return false;
    }
}
