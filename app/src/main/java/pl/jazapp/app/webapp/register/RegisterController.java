package pl.jazapp.app.webapp.register;

import webapp.login.LoginRequest;
import webapp.login.RegisterRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class RegisterController {

    public String register(RegisterRequest registerRequest) {
        System.out.println(String.format("Tried to register with username %s and password %s",registerRequest.getUsername(), registerRequest.getPassword()));
        // TODO code to register
        return "/login.xhtml?faces-redirect=true";
    }
}
