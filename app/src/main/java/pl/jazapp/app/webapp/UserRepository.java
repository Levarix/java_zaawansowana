package pl.jazapp.app.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.webapp.register.RegisterRequest;

import javax.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserRepository {
    private Map<String, User> users = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        users.put("test", new User("test", "test"));
    }

     public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public void createUser (RegisterRequest registerRequest) {
        users.put(registerRequest.getUsername(), new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getFirstName().substring(0, 1).toUpperCase() + registerRequest.getFirstName().substring(1), registerRequest.getLastName().substring(0, 1).toUpperCase() + registerRequest.getLastName().substring(1), registerRequest.getBirthDate(), registerRequest.getPassword()));
        logger.info(String.format("Created user with username: %s and password: %s", registerRequest.getUsername(), registerRequest.getPassword()));
    }
}

