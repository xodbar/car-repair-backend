package app.core.useCase.vehicle.category;

import app.core.domain.vehicle.category.VehicleCategory;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AddVehicleCategoryUseCase implements UseCase<AddVehicleCategoryUseCase.Input, AddVehicleCategoryUseCase.Output> {

    private final VehicleCategoryService vehicleCategoryService;

    public AddVehicleCategoryUseCase(VehicleCategoryService vehicleCategoryService) {
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @Override
    public Output handle(Input input) throws ApplicationException {
        if (vehicleCategoryService.getByName(input.name) != null)
            throw new ApplicationException("Vehicle category with name " + input.name + " is already exist", HttpStatus.BAD_REQUEST);

        if (input.additionalPercent < 0)
            throw new ApplicationException("Additional cost must be greater than or equals 0", HttpStatus.BAD_REQUEST);

        return new Output(vehicleCategoryService.createCategory(input.name, input.additionalPercent));
    }

    public record Input(
            String name,
            Integer additionalPercent
    ) implements Serializable {
    }

    public record Output(
            VehicleCategory vehicleCategory
    ) implements Serializable {
    }
}
