package pl.jazapp.app.webapp;

import javax.enterprise.context.ApplicationScoped;


import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserRepository {
    private Map<String, User> users = new ConcurrentHashMap<>();

    public UserRepository() {
        users.put("test", new User("test", "test"));
    }

     public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}

