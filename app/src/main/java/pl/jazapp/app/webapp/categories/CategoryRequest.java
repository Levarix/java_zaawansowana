package pl.jazapp.app.webapp.categories;

import pl.jazapp.app.webapp.departments.DepartmentEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class CategoryRequest {

    private  Long id;
    private String name;
    private Long departmentId;

    public CategoryRequest(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
        this.departmentId = categoryEntity.getDepartmentEntity().getId();
    }

    public CategoryRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public CategoryEntity categoryEntity() {
        var categoryEntity = new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntity.setName(name);

        return categoryEntity;
    }
}
