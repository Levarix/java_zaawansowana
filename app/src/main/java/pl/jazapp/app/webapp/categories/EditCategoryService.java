package pl.jazapp.app.webapp.categories;

import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.departments.DepartmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class EditCategoryService {


    @PersistenceContext
    private EntityManager em;

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    public boolean editCategory(Long categoryId,  String name, Long departmentId) {

        CategoryEntity categoryEntity = em.find(CategoryEntity.class ,categoryId);
        DepartmentEntity departmentEntity = em.find(DepartmentEntity.class ,departmentId);

        em.getTransaction().begin();

        categoryEntity.setName(name);
        categoryEntity.setDepartmentEntity(departmentEntity);

        em.getTransaction().commit();

        return true;
    }
}
