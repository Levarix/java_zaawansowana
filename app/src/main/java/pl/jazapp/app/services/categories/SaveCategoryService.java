package pl.jazapp.app.services.categories;

import pl.jazapp.app.webapp.categories.CategoryEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class SaveCategoryService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(CategoryEntity categoryEntity) {
        if(categoryEntity.getId() == null) {
            em.persist(categoryEntity);
        } else {
            em.merge(categoryEntity);
        }
    }
}
