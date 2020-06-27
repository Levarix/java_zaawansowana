package pl.jazapp.app.webapp.categories.services;

import pl.jazapp.app.webapp.categories.CategoryEntity;
import pl.jazapp.app.webapp.categories.CategoryRepository;
import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.webapp.departments.DepartmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CategoryGetAllService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    CategoryRepository categoryRepository;

    public ArrayList<CategoryEntity> getAll() {
        return categoryRepository.getAll();
    }
}
