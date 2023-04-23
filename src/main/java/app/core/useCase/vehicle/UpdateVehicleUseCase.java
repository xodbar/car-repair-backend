package app.core.useCase.vehicle;

import app.core.domain.client.dto.Client;
import app.core.domain.client.service.ClientService;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.service.VehicleService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateVehicleUseCase implements UseCase<UpdateVehicleUseCase.Input, UpdateVehicleUseCase.Output> {

    private final VehicleService vehicleService;

    private final ClientService clientService;

    public UpdateVehicleUseCase(VehicleService vehicleService, ClientService clientService) {
        this.vehicleService = vehicleService;
        this.clientService = clientService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        if (vehicleService.getById(input.id) == null)
            throw new ApplicationException("Vehicle with ID " + input.id + " does not exist", HttpStatus.BAD_REQUEST);

        Client owner = clientService.getById(input.ownerId);

        if (owner == null)
            throw new ApplicationException("Client with ID " + input.ownerId + " does not exist", HttpStatus.BAD_REQUEST);

        Vehicle updated = vehicleService.update(
                input.id,
                owner.id(),
                input.licensePlate
        );

        return new UpdateVehicleUseCase.Output(updated);
    }

    public record Input(
            Long id,
            Long ownerId,
            String licensePlate
    ) {
    }

    public record Output(Vehicle vehicle) {
    }
}
