package pl.jazapp.app.webapp.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.webapp.login.LoginRequest;
import pl.jazapp.app.webapp.login.LoginService;
import pl.jazapp.app.webapp.users.UserCreatorService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    private UserCreatorService userCreator;

    public String register(RegisterRequest registerRequest) {
        logger.info(String.format(
                "Tried to register with username %s and password %s",
                registerRequest.getUsername(),
                registerRequest.getPassword())
        );

        if (registerRequest.getPassword().equals(registerRequest.getPasswordCheck())) {

            var registered = userCreator.createUser(
                    registerRequest.getUsername(),
                    registerRequest.getPassword(),
                    registerRequest.getBirthDate(),
                    registerRequest.getEmail(),
                    registerRequest.getFirstName().substring(0, 1).toUpperCase() + registerRequest.getFirstName().substring(1),
                    registerRequest.getLastName().substring(0, 1).toUpperCase() + registerRequest.getLastName().substring(1)
            );

            if (registered) {
                return "/login.xhtml?faces-redirect=true";
            }else {
                return "/register.xhtml?faces-redirect=true";
            }
        }
        else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Passwords do not match");
            return "/register.xhtml?faces-redirect=true";

        }
    }
}
