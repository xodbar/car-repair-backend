package app.core.domain.client.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.category.service.ClientCategoryService;
import app.core.domain.client.dto.Client;
import app.core.domain.client.model.ClientModel;
import app.core.domain.client.repo.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientCategoryService clientCategoryService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientCategoryService clientCategoryService) {
        this.clientRepository = clientRepository;
        this.clientCategoryService = clientCategoryService;
    }

    @Override
    public Client addNewClient(String firstName, String lastName, String phone, Long clientCategoryId) {
        ClientCategoryModel clientCategoryModel = clientCategoryService.getModelById(clientCategoryId);

        return clientRepository.save(new ClientModel(
                -1L,
                firstName,
                lastName,
                phone,
                clientCategoryModel,
                List.of()
        )).toDto();
    }

    @Override
    public Client getById(Long id) {
        if ()
    }

    @Override
    public ClientModel getModelById(Long id) {
        return null;
    }

    @Override
    public Client getByPhone(String phone) {
        return null;
    }

    @Override
    public Client update(String firstName, String lastName, String phone, ClientCategory clientCategory) {
        return null;
    }
}
