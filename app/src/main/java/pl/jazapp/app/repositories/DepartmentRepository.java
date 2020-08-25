package pl.jazapp.app.repositories;

import pl.jazapp.app.entities.DepartmentEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<DepartmentEntity> findByName(String name) {
        return findOrEmpty(() ->
                em.createQuery(
                        "SELECT d from DepartmentEntity d WHERE d.name = :name",DepartmentEntity.class).
                        setParameter("name", name).getSingleResult());
    }

    public Optional<DepartmentEntity> findById(Long departmentId) {
        return Optional.ofNullable(em.find(DepartmentEntity.class, departmentId));
    }

    public List<DepartmentEntity> getAll() {
        TypedQuery<DepartmentEntity> getAllQuery = em.createQuery("SELECT d FROM DepartmentEntity d", DepartmentEntity.class);
        return getAllQuery.getResultList();
    }

    public static <T> Optional<T> findOrEmpty(final pl.jazapp.app.webapp.DaoRetriever<T> retriever) {
        try {
            return Optional.of(retriever.retrieve());
        } catch (NoResultException ex) {
            //log
        }
        return Optional.empty();
    }
}
