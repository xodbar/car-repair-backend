package app.core.useCase.employee;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.employee.service.EmployeeService;
import app.core.domain.vehicle.category.VehicleCategory;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddEmployeeUseCase implements UseCase<AddEmployeeUseCase.Input, AddEmployeeUseCase.Output> {

    private final EmployeeService employeeService;

    private final WorkCategoryService workCategoryService;

    private final VehicleCategoryService vehicleCategoryService;

    public AddEmployeeUseCase(EmployeeService employeeService, WorkCategoryService workCategoryService, VehicleCategoryService vehicleCategoryService) {
        this.employeeService = employeeService;
        this.workCategoryService = workCategoryService;
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        WorkCategory workCategory = workCategoryService.getById(input.specializedWorkCategoryId);
        VehicleCategory vehicleCategory = vehicleCategoryService.getById(input.specializedVehicleCategoryId);

        if (workCategory == null)
            throw new ApplicationException("Work category with ID " + input.specializedWorkCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (vehicleCategory == null)
            throw new ApplicationException("Vehicle category with ID " + input.specializedVehicleCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        Employee newEmployee = employeeService.addNewEmployee(
                input.firstName,
                input.lastName,
                input.avatarUrl,
                input.employeeGrade,
                workCategory.id(),
                vehicleCategory.id()
        );

        return new Output(newEmployee);
    }

    public record Input(
            String firstName,
            String lastName,
            String avatarUrl,
            EmployeeGrade employeeGrade,
            Long specializedWorkCategoryId,
            Long specializedVehicleCategoryId
    ) {
    }

    public record Output(Employee employee) {
    }
}
