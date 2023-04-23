package app.core.useCase.vehicle;

import app.core.domain.client.dto.Client;
import app.core.domain.client.service.ClientService;
import app.core.domain.vehicle.category.VehicleCategory;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.service.VehicleService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class AddVehicleUseCase implements UseCase<AddVehicleUseCase.Input, AddVehicleUseCase.Output> {

    private final VehicleService vehicleService;

    private final VehicleCategoryService vehicleCategoryService;

    private final ClientService clientService;

    public AddVehicleUseCase(VehicleService vehicleService, VehicleCategoryService vehicleCategoryService, ClientService clientService) {
        this.vehicleService = vehicleService;
        this.vehicleCategoryService = vehicleCategoryService;
        this.clientService = clientService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        if (vehicleService.getByLicensePlate(input.licensePlate) != null)
            throw new ApplicationException("Vehicle with license plate " + input.licensePlate + " is already exists", HttpStatus.BAD_REQUEST);

        if (input.issueYear >= LocalDate.now().getYear() || input.issueYear < LocalDate.EPOCH.getYear())
            throw new ApplicationException("Incorrect vehicle issue year (must be 1970 <= X <= 2023)", HttpStatus.BAD_REQUEST);

        VehicleCategory vehicleCategory = vehicleCategoryService.getById(input.vehicleCategoryId);
        Client owner = clientService.getById(input.ownerId);

        if (vehicleCategory == null)
            throw new ApplicationException("Vehicle category by ID " + input.vehicleCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (owner == null)
            throw new ApplicationException("Client with ID " + input.ownerId + " does not exist", HttpStatus.BAD_REQUEST);

        Vehicle newVehicle = vehicleService.addNewVehicle(
                input.modelName,
                vehicleCategory.id(),
                owner.id(),
                input.licensePlate,
                input.issueYear
        );

        return new Output(newVehicle);
    }

    public record Input(
            @NotNull
            String modelName,
            @NotNull
            Long vehicleCategoryId,
            @NotNull
            Long ownerId,
            @NotNull
            String licensePlate,
            @NotNull
            @Min(1970)
            Integer issueYear
    ) {
    }

    public record Output(Vehicle vehicle) {
    }
}
