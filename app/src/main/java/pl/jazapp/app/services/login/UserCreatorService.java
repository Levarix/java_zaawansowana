package pl.jazapp.app.services.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jazapp.app.entities.UserEntity;
import pl.jazapp.app.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserCreatorService {
    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Inject
    UserRepository userRepository;

    @Transactional
    public boolean createUser(
            String username,
            String password,
            String birthdate,
            String email,
            String first_name,
            String last_name
    ) {
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setBirthdate(birthdate);
        userEntity.setEmail(email);
        userEntity.setFirst_name(first_name);
        userEntity.setLast_name(last_name);

        if (userRepository.findByUsername(username).isPresent()) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Username is already taken!");
            return false;
        }
        if(userRepository.findByEmail(email).isPresent()) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .put("error-message", "Email is already taken!");
            return false;
        }

            if(passwordEncoder.matches(password, userEntity.getPassword())){
                em.persist(userEntity);
                return true;
            } else {
                return false;
            }
    }
}
