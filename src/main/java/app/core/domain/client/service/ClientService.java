package app.core.domain.client.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.dto.Client;
import app.core.domain.client.model.ClientModel;

import java.util.List;

public interface ClientService {
    Client addNewClient(String firstName, String lastName, String phone, Long clientCategoryId);

    Client getById(Long id);

    ClientModel getModelById(Long id);

    Client getByPhone(String phone);

    List<Client> getAll();

    Client update(Long id, String firstName, String lastName, String phone, ClientCategory clientCategory);
}
