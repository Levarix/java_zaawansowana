package webapp.login;

import pl.jazapp.app.UserMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginController {
    @Inject
    UserMap userMap;

    public String login(LoginRequest loginRequest) {
        System.out.println(String.format("Tried to login with username %s and password %s",loginRequest.getUsername(), loginRequest.getPassword()));
        // TODO code to log in and session
        return "/index.xhtml?faces-redirect=true";
    }
}
