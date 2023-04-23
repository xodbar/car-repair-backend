package app.core.domain.vehicle.category;

public interface VehicleCategoryService {
    VehicleCategory createCategory(String name, Integer additionalCost);

    VehicleCategory getByName(String name);

    VehicleCategory getById(Long id);

    VehicleCategoryModel getModelById(Long id);
}
