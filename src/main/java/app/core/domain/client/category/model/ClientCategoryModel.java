package app.core.domain.client.category.model;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.model.ClientModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client_categories")
public class ClientCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @OneToMany(mappedBy = "clientCategory", fetch = FetchType.LAZY)
    private List<ClientModel> clients;

    public ClientCategoryModel() {
    }

    public ClientCategoryModel(Long id, String name, Integer discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    public ClientCategory toDto() {
        return new ClientCategory(id, name);
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<ClientModel> getClients() {
        return clients;
    }
}
