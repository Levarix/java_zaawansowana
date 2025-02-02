package pl.jazapp.app.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.users.UserContext;
import pl.jazapp.app.webapp.UserRepository;
import pl.jazapp.app.webapp.users.UserEntity;

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

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginService() {
        this(new UserRepository());
    }

    public LoginService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean login(LoginRequest loginRequest) {

        Optional<UserEntity> checkingUser = userRepository.findByUsername(loginRequest.getUsername());

        if (checkingUser.isPresent()) {
            UserEntity existingCheckingUser = checkingUser.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), existingCheckingUser.getPassword())) {
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
