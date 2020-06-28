package pl.jazapp.app.repositories;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.departments.DepartmentEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<CategoryEntity> findByName(String name) {
        return findOrEmpty(() ->
                em.createQuery(
                        "SELECT d from CategoryEntity d WHERE d.name = :name",CategoryEntity.class).
                        setParameter("name", name).getSingleResult());
    }

    public CategoryEntity findById(Long departmentId) {
        return em.find(CategoryEntity.class, departmentId);
    }

    public ArrayList<CategoryEntity> getAll() {
        TypedQuery<CategoryEntity> getAllQuery = em.createQuery("SELECT d FROM CategoryEntity d", CategoryEntity.class);
        return ((ArrayList<CategoryEntity>) getAllQuery.getResultList());
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
