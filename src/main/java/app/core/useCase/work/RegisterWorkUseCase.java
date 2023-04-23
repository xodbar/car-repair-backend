package app.core.useCase.work;

import app.core.domain.bill.BillGeneratorService;
import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.service.EmployeeService;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.service.VehicleService;
import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.domain.work.dto.Work;
import app.core.domain.work.service.WorkService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class RegisterWorkUseCase implements UseCase<RegisterWorkUseCase.Input, RegisterWorkUseCase.Output> {

    private final WorkService workService;

    private final WorkCategoryService workCategoryService;

    private final VehicleService vehicleService;

    private final EmployeeService employeeService;

    private final BillGeneratorService billGeneratorService;

    public RegisterWorkUseCase(WorkService workService, WorkCategoryService workCategoryService, VehicleService vehicleService, EmployeeService employeeService, BillGeneratorService billGeneratorService) {
        this.workService = workService;
        this.workCategoryService = workCategoryService;
        this.vehicleService = vehicleService;
        this.employeeService = employeeService;
        this.billGeneratorService = billGeneratorService;
    }

    @Override
    public Output handle(Input input) throws ApplicationException {
        WorkCategory workCategory = workCategoryService.getById(input.workCategoryId);
        Vehicle vehicle = vehicleService.getById(input.vehicleId);
        Employee employee = employeeService.getById(input.employeeId);

        if (workCategory == null)
            throw new ApplicationException("Work category with ID " + input.workCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (vehicle == null)
            throw new ApplicationException("Vehicle by ID " + input.vehicleId + " does not exist", HttpStatus.BAD_REQUEST);

        if (employee == null)
            throw new ApplicationException("Employee with ID " + input.employeeId + " does not exist", HttpStatus.BAD_REQUEST);

        if (input.plannedEndDate.isBefore(LocalDate.now()))
            throw new ApplicationException("Planned date cannot be before current date", HttpStatus.BAD_REQUEST);

        if (input.initialPrice < 100)
            throw new ApplicationException("Initial price must be greater than 100", HttpStatus.BAD_REQUEST);

        int totalPrice = input.initialPrice;
        int workCategoryMismatch = 0;
        int vehicleCategoryMismatch = 0;
        int employeeGradeCost;
        int vehicleCategoryCost;
        int clientCategoryDiscount;

        if (!Objects.equals(workCategory.id(), employee.specializedWorkCategory().id()))
            workCategoryMismatch = (int) Math.round(totalPrice * 0.1);

        if (!Objects.equals(vehicle.vehicleCategory().id(), employee.specializedVehicleCategory().id()))
            vehicleCategoryMismatch = (int) Math.round(totalPrice * 0.1);

        employeeGradeCost = (totalPrice * employee.employeeGrade().getAdditionalGradePercent()) / 100;
        vehicleCategoryCost = (totalPrice * vehicle.vehicleCategory().additionalCost()) / 100;
        totalPrice = totalPrice + (workCategoryMismatch + vehicleCategoryMismatch + employeeGradeCost + vehicleCategoryCost);

        clientCategoryDiscount = (totalPrice * vehicle.owner().clientCategory().discount()) / 100;

        int totalPriceAfterDiscount = totalPrice - clientCategoryDiscount;

        Work registeredWork = workService.registerNewWork(
                workCategory.id(),
                vehicle.id(),
                employee.id(),
                totalPriceAfterDiscount,
                input.plannedEndDate
        );

        billGeneratorService.generateBill(
                registeredWork,
                vehicle.owner(),
                employee,
                vehicle,
                input,
                new BillGeneratorService.GenerateBillPrices(
                        workCategoryMismatch,
                        vehicleCategoryMismatch,
                        employeeGradeCost,
                        vehicleCategoryCost,
                        clientCategoryDiscount
                )
        );

        return new Output(registeredWork);
    }

    public record Input(
            @NotNull
            Long workCategoryId,
            @NotNull
            Long vehicleId,
            @NotNull
            Long employeeId,
            @NotNull
            @Min(100)
            Integer initialPrice,
            @NotNull
            LocalDate plannedEndDate
    ) {
    }

    public record Output(Work work) {
    }
}
