package pl.jazapp.app.webapp;

import javax.persistence.NoResultException;

@FunctionalInterface
public interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
}