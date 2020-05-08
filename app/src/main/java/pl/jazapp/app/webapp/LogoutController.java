package pl.jazapp.app.webapp;

public class LogoutController {

    public String logout() {
        // userContext.setUserId(null)
        //userContext.logout();
        //userContext.setUsername();
        return "login.xhtml?faces-redirect=true";
    }
}
