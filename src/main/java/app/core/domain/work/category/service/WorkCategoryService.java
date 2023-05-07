package app.core.domain.work.category.service;

import app.core.domain.employee.dto.Employee;
import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.model.WorkCategoryModel;

import java.util.List;

public interface WorkCategoryService {
    WorkCategory createCategory(String name);

    WorkCategory getByName(String name);

    WorkCategory getById(Long id);

    WorkCategoryModel getModelById(Long id);

    List<WorkCategory> getAll();

    List<Employee> getAllSpecializedEmployees(String name);
}
