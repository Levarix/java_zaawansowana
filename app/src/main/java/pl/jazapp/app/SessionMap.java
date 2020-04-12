package pl.jazapp.app;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ApplicationScoped
public class SessionMap {
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    public Session getSession(String sessionId) {
        if(!sessions.containsKey(sessionId)) {
            sessions.put(sessionId, new Session());
        }
        return sessions.get(sessionId);
    }

    public static class Session {
        private boolean isLogged = false;

        public void logIn() {
            if (!isLogged) {
                isLogged = true;
            }
        }

        public boolean isLogged() {
            return isLogged;
        }
    }
}
