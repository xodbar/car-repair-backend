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
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Component
public class UpdateEmployeeUseCase implements UseCase<UpdateEmployeeUseCase.Input, UpdateEmployeeUseCase.Output> {

    private final EmployeeService employeeService;

    private final WorkCategoryService workCategoryService;

    private final VehicleCategoryService vehicleCategoryService;

    public UpdateEmployeeUseCase(EmployeeService employeeService, WorkCategoryService workCategoryService, VehicleCategoryService vehicleCategoryService) {
        this.employeeService = employeeService;
        this.workCategoryService = workCategoryService;
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        Employee employee = employeeService.getById(input.id);

        if (employee == null)
            throw new ApplicationException("Employee by ID " + input.id + " does not exist", HttpStatus.BAD_REQUEST);

        WorkCategory workCategory = workCategoryService.getById(input.specializedWorkCategoryId);
        VehicleCategory vehicleCategory = vehicleCategoryService.getById(input.specializedVehicleCategoryId);

        if (workCategory == null)
            throw new ApplicationException("Work category by ID " + input.specializedWorkCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (vehicleCategory == null)
            throw new ApplicationException("Vehicle category by ID " + input.specializedVehicleCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        employee = employeeService.update(
                employee.id(),
                input.firstName,
                input.lastName,
                input.avatarUrl,
                input.employeeGrade,
                workCategory.id(),
                vehicleCategory.id()
        );

        return new Output(employee);
    }

    public record Input(
            @NotNull
            Long id,
            String firstName,
            String lastName,
            String avatarUrl,
            EmployeeGrade employeeGrade,
            Long specializedWorkCategoryId,
            Long specializedVehicleCategoryId
    ) implements Serializable {
    }

    public record Output(
            Employee employee
    ) implements Serializable {
    }
}
