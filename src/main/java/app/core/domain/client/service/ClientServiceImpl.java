package app.core.domain.client.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.category.service.ClientCategoryService;
import app.core.domain.client.dto.Client;
import app.core.domain.client.model.ClientModel;
import app.core.domain.client.repo.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (clientRepository.findById(id).isPresent())
            return clientRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public ClientModel getModelById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public Client getByPhone(String phone) {
        if (clientRepository.findByPhone(phone) != null)
            return clientRepository.findByPhone(phone).toDto();

        return null;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Client update(Long id, String firstName, String lastName, String phone, ClientCategory clientCategory) {
        if (getById(id) == null)
            return null;

        ClientModel clientModel = getModelById(id);
        ClientCategoryModel clientCategoryModel = clientCategory == null ? null :
                clientCategoryService.getModelById(clientCategory.id());

        if (firstName != null && !firstName.isEmpty())
            clientModel.setFirstName(firstName);

        if (lastName != null && !lastName.isEmpty())
            clientModel.setLastName(lastName);

        if (phone != null && !phone.isEmpty())
            clientModel.setPhone(phone);

        if (clientCategoryModel != null)
            clientModel.setClientCategory(clientCategoryModel);

        return clientRepository.save(clientModel).toDto();
    }
}
