package app.core.domain.work.model;

import app.core.domain.employee.model.EmployeeModel;
import app.core.domain.vehicle.model.VehicleModel;
import app.core.domain.work.category.model.WorkCategoryModel;
import app.core.domain.work.dto.Work;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "works")
public class WorkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "work_category_id")
    private WorkCategoryModel workCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    private VehicleModel vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "planned_end_date", nullable = false)
    private LocalDate plannedEndDate;

    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    public WorkModel() {
    }

    public WorkModel(Long id, WorkCategoryModel workCategory, VehicleModel vehicle, EmployeeModel employee, Integer totalPrice, LocalDate plannedEndDate) {
        this.id = id;
        this.workCategory = workCategory;
        this.vehicle = vehicle;
        this.employee = employee;
        this.totalPrice = totalPrice;
        this.plannedEndDate = plannedEndDate;
    }

    public Work toDto() {
        return new Work(
                id,
                workCategory.toDto(),
                vehicle.toDto(),
                employee.toDto(),
                totalPrice,
                plannedEndDate,
                actualEndDate
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkCategoryModel getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(WorkCategoryModel workCategory) {
        this.workCategory = workCategory;
    }

    public VehicleModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleModel vehicle) {
        this.vehicle = vehicle;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(LocalDate plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }
}
