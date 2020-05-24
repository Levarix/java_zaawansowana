package pl.jazapp.app.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    LoginService loginService;

    public String login(LoginRequest loginRequest) {
       logger.info(String
               .format("Tried to login with username %s and password %s",
                       loginRequest.getUsername(),
                       loginRequest.getPassword()));

       var logged = loginService.login(loginRequest);

       if (logged) {
           return "/index.xhtml?faces-redirect=true";
       } else {
           return "/login.xhtml?faces-redirect=true";
       }
    }
    public String logout() {
        loginService.logout();
        return "/login.xhtml?faces-redirect=true";
    }
}
