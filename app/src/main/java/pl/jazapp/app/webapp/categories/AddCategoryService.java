package pl.jazapp.app.webapp.categories;

import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.departments.DepartmentRepository;
import pl.jazapp.app.webapp.users.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@RequestScoped
public class AddCategoryService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    DepartmentRepository departmentRepository;

    @Transactional
    public boolean createCategory(String name, Long departmentId) {
        var categoryEntity = new CategoryEntity();
        DepartmentEntity departmentEntity = em.find(DepartmentEntity.class, departmentId);

            categoryEntity.setName(name);
            categoryEntity.setDepartmentEntity(departmentEntity);

            em.persist(categoryEntity);
            em.persist(departmentEntity);

            return true;
        }
}
