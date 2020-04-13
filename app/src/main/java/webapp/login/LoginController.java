package webapp.login;


import pl.jazapp.app.webapp.UserContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginController {
    @Inject
    LoginService loginService;
    @Inject
    UserContext userContext;

    public String login(LoginRequest loginRequest) {
        System.out.println(String.format("Tried to login with username %s and password %s",loginRequest.getUsername(), loginRequest.getPassword()));

        loginService.login(loginRequest);

        if(userContext.isLogged()) {
            return "/index.xhtml?faces-redirect=true";
        } else {
            return "/login.xhtml?faces-redirect=true";
        }
    }
}
