package pl.jazapp.app.webapp.categories;


import pl.jazapp.app.repositories.CategoryRepository;
import pl.jazapp.app.services.categories.SaveCategoryService;
import pl.jazapp.app.webapp.departments.DepartmentEntity;
import pl.jazapp.app.repositories.DepartmentRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class CategoryController {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    DepartmentRepository departmentRepository;

    @Inject
    SaveCategoryService saveCategoryService;


    public List<CategoryEntity> getCategoryList() {
        return categoryRepository.getAll();
    }

    public List<DepartmentEntity> getDepartmentList() {
        return departmentRepository.getAll();
    }

    private CategoryRequest categoryRequest;

    public CategoryRequest getCategoryRequest() {
        Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(categoryRequest == null) {
            if (param.containsKey("categoryId")) {
                var categoryId = Long.parseLong(param.get("categoryId"));
                var categoryEntity = categoryRepository.findById(categoryId);

                categoryRequest = new CategoryRequest(categoryEntity);
            } else {
                categoryRequest = new CategoryRequest();
            }
        }
        return categoryRequest;
    }

    public String edit() {

        var departmentEntity = departmentRepository.findById(categoryRequest.getDepartmentId()).get();
        var categoryEntity = categoryRequest.categoryEntity();

        categoryEntity.setDepartmentEntity(departmentEntity);
        saveCategoryService.save(categoryEntity);

        return "/categories/list.xhtml?faces-redirect=true";
    }

}
