package app.core.domain.client.category.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.category.repo.ClientCategoryRepository;
import app.core.domain.client.dto.Client;
import app.core.domain.client.model.ClientModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientCategoryServiceImpl implements ClientCategoryService {

    private final ClientCategoryRepository clientCategoryRepository;

    public ClientCategoryServiceImpl(ClientCategoryRepository clientCategoryRepository) {
        this.clientCategoryRepository = clientCategoryRepository;
    }

    @Override
    public ClientCategory createCategory(String name, Integer discountAmount) {
        return clientCategoryRepository.save(new ClientCategoryModel(-1L, name, discountAmount)).toDto();
    }

    @Override
    public List<ClientCategory> getAll() {
        return clientCategoryRepository.findAll().stream().map(ClientCategoryModel::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientCategory getByName(String name) {
        if (clientCategoryRepository.findByName(name) != null)
            return clientCategoryRepository.findByName(name).toDto();

        return null;
    }

    @Override
    public ClientCategory getById(Long id) {
        if (clientCategoryRepository.findById(id).isPresent())
            return clientCategoryRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public ClientCategoryModel getModelById(Long id) {
        return clientCategoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Client> getClientsByName(String name) {
        return clientCategoryRepository.findByName(name)
                .getClients()
                .stream()
                .map(ClientModel::toDto)
                .collect(Collectors.toList());
    }
}
