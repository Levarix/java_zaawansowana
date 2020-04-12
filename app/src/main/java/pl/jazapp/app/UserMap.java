package pl.jazapp.app;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserMap {
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
}
