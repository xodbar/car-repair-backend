package app.core.domain.vehicle.model;

import app.core.domain.client.model.ClientModel;
import app.core.domain.vehicle.category.VehicleCategoryModel;
import app.core.domain.vehicle.dto.Vehicle;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_category_id")
    private VehicleCategoryModel vehicleCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private ClientModel owner;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "issue_year", nullable = false)
    private Integer issueYear;

    public VehicleModel() {
    }

    public VehicleModel(Long id, String modelName, VehicleCategoryModel vehicleCategory, ClientModel owner, String licensePlate, Integer issueYear) {
        this.id = id;
        this.modelName = modelName;
        this.vehicleCategory = vehicleCategory;
        this.owner = owner;
        this.licensePlate = licensePlate;
        this.issueYear = issueYear;
    }

    public Vehicle toDto() {
        return new Vehicle(
                id,
                modelName,
                vehicleCategory.toDto(),
                owner.toLightweightDto(),
                licensePlate,
                issueYear
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public VehicleCategoryModel getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategoryModel vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public ClientModel getOwner() {
        return owner;
    }

    public void setOwner(ClientModel owner) {
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Integer issueYear) {
        this.issueYear = issueYear;
    }
}
