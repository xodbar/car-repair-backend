package app.core.domain.client.category.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.category.repo.ClientCategoryRepository;
import org.springframework.stereotype.Service;

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
    public ClientCategory getByName(String name) {
        return clientCategoryRepository.findByName(name).toDto();
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
}
