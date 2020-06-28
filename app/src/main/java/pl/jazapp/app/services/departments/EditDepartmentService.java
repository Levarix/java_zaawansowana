package pl.jazapp.app.services.departments;

import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.repositories.DepartmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class EditDepartmentService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    DepartmentRepository departmentRepository;

    @Transactional
    public boolean editDepartment(Long departmentId,  String name) {

        DepartmentEntity departmentEntity = em.find(DepartmentEntity.class ,departmentId);

        em.getTransaction().begin();
        departmentEntity.setName(name);
        em.getTransaction().commit();
        return true;
    }
}
