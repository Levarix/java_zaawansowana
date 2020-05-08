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
        users.put("test", new User("test", "rybacki@wp.pl", "Maciej","Testowy", "03/08/1998", "test"));
    }

     public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public void createUser (RegisterRequest registerRequest) {
        users.put(registerRequest.getUsername(), new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getBirthDate(), registerRequest.getPassword()));
        logger.info(String.format("Created user with username: %s and password: %s", registerRequest.getUsername(), registerRequest.getPassword()));
    }
}
