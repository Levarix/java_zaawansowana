package pl.jazapp.app.webapp.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.webapp.login.LoginRequest;
import pl.jazapp.app.webapp.login.LoginService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    RegisterService registerService;


    public String register(RegisterRequest registerRequest) {
        logger.info(String.format("Tried to register with username %s and password %s",registerRequest.getUsername(), registerRequest.getPassword()));

        var registered = registerService.register(registerRequest);

        if (registered == true) {
            return "/login.xhtml?faces-redirect=true";
        }else {
            return "/register.xhtml?faces-redirect=true";
        }
    }
}
