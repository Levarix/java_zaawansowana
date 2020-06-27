package pl.jazapp.app.webapp.departments.services;

import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.departments.DepartmentRepository;
import pl.jazapp.app.webapp.users.UserEntity;
import pl.jazapp.app.webapp.users.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class AddDepartmentService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    DepartmentRepository departmentRepository;

    @Transactional
    public boolean createDepartment(String name) {
        var departmentEntity = new DepartmentEntity();

        departmentEntity.setName(name);

        em.persist(departmentEntity);
        return true;
    }

}
