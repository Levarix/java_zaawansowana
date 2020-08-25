package pl.jazapp.app.entities;

import pl.jazapp.app.entities.CategoryEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department", schema = "public")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "departmentEntity")
    private List<CategoryEntity> categoryEntities;

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

    public List<CategoryEntity> getCategoryEntities() {
        return categoryEntities;
    }

    public void setCategoryEntities(List<CategoryEntity> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    public void setCategoryEntityListItem (CategoryEntity categoryEntityItem) {
        this.categoryEntities.add(categoryEntityItem);
    }
}
