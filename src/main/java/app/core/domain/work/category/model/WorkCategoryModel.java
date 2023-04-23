package app.core.domain.work.category.model;

import app.core.domain.employee.model.EmployeeModel;
import app.core.domain.work.category.dto.WorkCategory;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "work_category")
public class WorkCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specializedWorkCategory")
    private List<EmployeeModel> specializedEmployees;

    public WorkCategoryModel() {
    }

    public WorkCategoryModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public WorkCategory toDto() {
        return new WorkCategory(id, name);
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

    public List<EmployeeModel> getCategoryEmployees() {
        return specializedEmployees;
    }
}
