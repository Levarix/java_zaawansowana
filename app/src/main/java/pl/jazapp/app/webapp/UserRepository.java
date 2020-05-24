package pl.jazapp.app.webapp;


import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@FunctionalInterface
interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
}

@ApplicationScoped
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<UserEntity> findByUsername(String username) {
        return findOrEmpty(() ->
                em.createQuery(
                        "SELECT u from UserEntity u WHERE u.username = :username", UserEntity.class).
                        setParameter("username", username).getSingleResult());
    }

    public Optional<UserEntity> findByEmail(String email) {
        return findOrEmpty(() ->
                em.createQuery(
                        "SELECT u from UserEntity u WHERE u.email = :email", UserEntity.class).
                        setParameter("email", email).getSingleResult());
    }

    public static <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
        try {
            return Optional.of(retriever.retrieve());
        } catch (NoResultException ex) {
            //log
        }
        return Optional.empty();
    }
}

