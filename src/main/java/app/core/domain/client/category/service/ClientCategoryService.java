package app.core.domain.client.category.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;
import app.core.domain.client.dto.Client;

import java.util.List;

public interface ClientCategoryService {
    ClientCategory createCategory(String name, Integer discountAmount);

    List<ClientCategory> getAll();

    ClientCategory getByName(String name);

    ClientCategory getById(Long id);

    ClientCategoryModel getModelById(Long id);

    List<Client> getClientsByName(String name);
}
