package pl.jazapp.app.webapp.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jazapp.app.entities.UserEntity;
import pl.jazapp.app.repositories.UserRepository;
import pl.jazapp.app.webapp.login.LoginRequest;
import pl.jazapp.app.services.login.LoginService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;


@SessionScoped
@Named
public class UserContext implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private String role = "DEFAULT";

    private Boolean isAdmin = false;

    private UserEntity userEntity;

    private boolean isLogged = false;

    @Inject
    UserRepository userRepository;

    public UserContext() {
    }

    public UserContext(boolean isLogged) {
        this.isLogged = isLogged;
    }


    public boolean isUserRoleAdmin() {
        return this.role.equals("ADMIN");
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String role) {
        isAdmin = (role.equals("ADMIN"));
    }

    public void login(LoginRequest loginRequest) {
        isLogged = true;
        Optional<UserEntity> user = userRepository.findByUsername(loginRequest.getUsername());
        if(user.isPresent()){
            UserEntity existingUser = user.get();
            setId(existingUser.getId());
            setFullName(existingUser.getFirst_name() + " " + existingUser.getLast_name());
            logger.warn(String.format("%s", role));
            setRole(existingUser.getRole());
            setIsAdmin(existingUser.getRole());
            logger.warn(String.format("%s", role));
            setUserEntity(existingUser);
        }

    }
    public void logout (){
        isLogged = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }


    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
