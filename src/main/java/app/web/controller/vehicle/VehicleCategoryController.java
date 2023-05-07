package app.web.controller.vehicle;

import app.core.domain.employee.dto.Employee;
import app.core.domain.vehicle.category.VehicleCategory;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.exception.ApplicationException;
import app.core.useCase.vehicle.category.AddVehicleCategoryUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-category")
public class VehicleCategoryController {

    private final AddVehicleCategoryUseCase addVehicleCategoryUseCase;

    private final VehicleCategoryService vehicleCategoryService;

    public VehicleCategoryController(AddVehicleCategoryUseCase addVehicleCategoryUseCase, VehicleCategoryService vehicleCategoryService) {
        this.addVehicleCategoryUseCase = addVehicleCategoryUseCase;
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @PostMapping
    @ResponseBody
    public AddVehicleCategoryUseCase.Output addVehicleCategory(AddVehicleCategoryUseCase.Input input) throws ApplicationException {
        return addVehicleCategoryUseCase.handle(input);
    }

    @GetMapping
    @ResponseBody
    public List<VehicleCategory> getAllCategories() {
        return vehicleCategoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}/vehicles")
    @ResponseBody
    public List<Vehicle> getAllVehiclesByCategory(@PathVariable String categoryName) {
        return vehicleCategoryService.getAllVehiclesByName(categoryName);
    }

    @GetMapping("/{categoryName}/employees")
    @ResponseBody
    public List<Employee> getAllSpecializedEmployees(@PathVariable String categoryName) {
        return vehicleCategoryService.getAllSpecializedEmployees(categoryName);
    }
}
