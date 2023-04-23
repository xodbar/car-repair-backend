package app.core.domain.client.model;

import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.dto.Client;
import app.core.domain.client.dto.LightweightClient;
import app.core.domain.vehicle.model.VehicleModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_category_id")
    private ClientCategoryModel clientCategory;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<VehicleModel> vehicles;

    public ClientModel() {
    }

    public ClientModel(Long id, String firstName, String lastName, String phone, ClientCategoryModel clientCategory, List<VehicleModel> vehicles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.clientCategory = clientCategory;
        this.vehicles = vehicles;
    }

    public Client toDto() {
        return new Client(
                id,
                firstName,
                lastName,
                phone,
                clientCategory.toDto(),
                vehicles.stream().map(VehicleModel::toDto).collect(Collectors.toList())
        );
    }

    public LightweightClient toLightweightDto() {
        return new LightweightClient(
                id,
                firstName,
                lastName,
                phone,
                clientCategory.toDto()
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientCategoryModel getClientCategory() {
        return clientCategory;
    }

    public void setClientCategory(ClientCategoryModel clientCategory) {
        this.clientCategory = clientCategory;
    }

    public List<VehicleModel> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleModel> vehicles) {
        this.vehicles = vehicles;
    }
}
