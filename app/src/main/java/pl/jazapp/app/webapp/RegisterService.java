package pl.jazapp.app.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
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

    void register (RegisterRequest registerRequest) {
        Optional<User> checkingUser = userRepository.findByUsername(registerRequest.getUsername());

        if (!checkingUser.isPresent()) {
                userRepository.createUser(registerRequest);
        } else {
            logger.warn(String.format("Username already exists: %s", registerRequest.getUsername()));
        }
    }
}
