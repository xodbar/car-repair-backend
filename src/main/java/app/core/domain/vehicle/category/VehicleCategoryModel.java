package app.core.domain.vehicle.category;

import app.core.domain.employee.EmployeeModel;
import app.core.domain.vehicle.model.VehicleModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicle_categories")
public class VehicleCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "additional_cost", nullable = false)
    private Integer additionalCost;

    @OneToMany(mappedBy = "specializedVehicleCategory", fetch = FetchType.LAZY)
    private List<EmployeeModel> specializedEmployees;

    @OneToMany(mappedBy = "vehicleCategory", fetch = FetchType.LAZY)
    private List<VehicleModel> vehicles;

    public VehicleCategoryModel() {
    }

    public VehicleCategoryModel(Long id, String name, Integer additionalCost) {
        this.id = id;
        this.name = name;
        this.additionalCost = additionalCost;
    }

    public VehicleCategory toDto() {
        return new VehicleCategory(id, name, additionalCost);
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

    public Integer getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Integer additionalCost) {
        this.additionalCost = additionalCost;
    }
}
