package app.core.domain.vehicle.category;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.model.EmployeeModel;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.model.VehicleModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;

    public VehicleCategoryServiceImpl(VehicleCategoryRepository vehicleCategoryRepository) {
        this.vehicleCategoryRepository = vehicleCategoryRepository;
    }

    @Override
    public VehicleCategory createCategory(String name, Integer additionalCost) {
        return vehicleCategoryRepository.save(new VehicleCategoryModel(-1L, name, additionalCost)).toDto();
    }

    @Override
    public VehicleCategory getByName(String name) {
        return vehicleCategoryRepository.findByName(name).toDto();
    }

    @Override
    public VehicleCategory getById(Long id) {
        return vehicleCategoryRepository.findById(id).orElseThrow().toDto();
    }

    @Override
    public VehicleCategoryModel getModelById(Long id) {
        return vehicleCategoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<VehicleCategory> getAllCategories() {
        return vehicleCategoryRepository.findAll().stream().map(VehicleCategoryModel::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getAllVehiclesByName(String name) {
        return vehicleCategoryRepository.findByName(name)
                .getVehicles()
                .stream()
                .map(VehicleModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllSpecializedEmployees(String name) {
        return vehicleCategoryRepository.findByName(name)
                .getSpecializedEmployees()
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }
}
