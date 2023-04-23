package app.core.domain.client.category.service;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.model.ClientCategoryModel;

public interface ClientCategoryService {
    ClientCategory createCategory(String name, Integer discountAmount);

    ClientCategory getByName(String name);

    ClientCategory getById(Long id);

    ClientCategoryModel getModelById(Long id);
}
