package app.core.domain.vehicle.category;

import app.core.domain.employee.dto.Employee;
import app.core.domain.vehicle.dto.Vehicle;

import java.util.List;

public interface VehicleCategoryService {
    VehicleCategory createCategory(String name, Integer additionalCost);

    VehicleCategory getByName(String name);

    VehicleCategory getById(Long id);

    VehicleCategoryModel getModelById(Long id);

    List<VehicleCategory> getAllCategories();

    List<Vehicle> getAllVehiclesByName(String name);

    List<Employee> getAllSpecializedEmployees(String name);
}
