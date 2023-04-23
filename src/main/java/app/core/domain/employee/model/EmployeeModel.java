package app.core.domain.employee.model;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.vehicle.category.VehicleCategoryModel;
import app.core.domain.work.category.model.WorkCategoryModel;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;

    @Enumerated(value = EnumType.STRING)
    private EmployeeGrade employeeGrade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "work_category_id")
    private WorkCategoryModel specializedWorkCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_category_id")
    private VehicleCategoryModel specializedVehicleCategory;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public EmployeeModel() {
    }

    public EmployeeModel(Long id, String firstName, String lastName, String avatarUrl, EmployeeGrade employeeGrade, WorkCategoryModel specializedWorkCategory, VehicleCategoryModel specializedVehicleCategory, Boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.employeeGrade = employeeGrade;
        this.specializedWorkCategory = specializedWorkCategory;
        this.specializedVehicleCategory = specializedVehicleCategory;
        this.isActive = isActive;
    }

    public Employee toDto() {
        return new Employee(
                id,
                firstName,
                lastName,
                avatarUrl,
                employeeGrade,
                specializedWorkCategory.toDto(),
                specializedVehicleCategory.toDto(),
                isActive
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public EmployeeGrade getEmployeeGrade() {
        return employeeGrade;
    }

    public void setEmployeeGrade(EmployeeGrade employeeGrade) {
        this.employeeGrade = employeeGrade;
    }

    public WorkCategoryModel getSpecializedWorkCategory() {
        return specializedWorkCategory;
    }

    public void setSpecializedWorkCategory(WorkCategoryModel specializedWorkCategory) {
        this.specializedWorkCategory = specializedWorkCategory;
    }

    public VehicleCategoryModel getSpecializedVehicleCategory() {
        return specializedVehicleCategory;
    }

    public void setSpecializedVehicleCategory(VehicleCategoryModel specializedVehicleCategory) {
        this.specializedVehicleCategory = specializedVehicleCategory;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
