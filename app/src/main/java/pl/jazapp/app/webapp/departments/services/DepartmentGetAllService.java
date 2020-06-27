package pl.jazapp.app.webapp.departments.services;


import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.departments.DepartmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class DepartmentGetAllService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAll() {
        return departmentRepository.getAll();
    }

}

