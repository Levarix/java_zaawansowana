package webapp.login;

import pl.jazapp.app.webapp.User;
import pl.jazapp.app.webapp.UserContext;
import pl.jazapp.app.webapp.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;


@RequestScoped
public class LoginService {
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    UserContext userContext;

    public LoginService() {
        this(new UserRepository());
    }

    public LoginService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void login(LoginRequest loginRequest) {

        Optional<User> checkingUser = userRepository.findByUsername(loginRequest.getUsername());

        if (checkingUser.isPresent()) {
            User existingCheckingUser = checkingUser.get();
            if (!existingCheckingUser.equals(null)) {
                if (existingCheckingUser.getPassword().equals(loginRequest.getPassword())) {
                    userContext.login();

                    logger.warn(String.format("Logged successfully with username: %s, and password: %s", loginRequest.getUsername(), loginRequest.getPassword()));
                } else {
                    logger.warn(String.format("Not found Username %s", loginRequest.getUsername()));
                }
            }
        }
    }
}
