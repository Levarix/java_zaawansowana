package pl.jazapp.app.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    LoginService loginService;

    public String login(LoginRequest loginRequest) {
       logger.info(String.format("Tried to login with username %s and password %s",loginRequest.getUsername(), loginRequest.getPassword()));
        loginService.login(loginRequest);

        return "/index.xhtml?faces-redirect=true";
    }
}
