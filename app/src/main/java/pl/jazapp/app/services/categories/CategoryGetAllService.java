package pl.jazapp.app.services.categories;

import pl.jazapp.app.repositories.CategoryRepository;
import pl.jazapp.app.entities.CategoryEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

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
