package pl.jazapp.app;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;;
import java.util.UUID;

@WebServlet("session")
public class SessionApp extends HttpServlet {
    @Inject
    SessionMap sessionMap;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var writer = resp.getWriter();

        var sessionId = Arrays.stream(req.getCookies())
                .filter(it -> it.getName().equals("MY_SESSION"))
                .map(Cookie::getValue).findFirst();

        if (sessionId.isEmpty()) {
            writer.println("I am logged: false");
            var newSessionId = UUID.randomUUID().toString();
            resp.setHeader("Set-Cookie", String.format("MY_SESSION=%s;Max-Age=8", newSessionId));
        } else {
            var session = sessionMap.getSession(sessionId.get());

            resp.setStatus(200);
            resp.setContentType("text/plain");

            writer.println(String.format("I am logged: %s", session.isLogged()));
            session.logIn();
            resp.setHeader("Set-Cookie", String.format("MY_SESSION=%s;Max-Age=8", sessionId.get()));
        }
    }
}