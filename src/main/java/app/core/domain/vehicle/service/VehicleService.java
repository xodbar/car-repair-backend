package app.core.domain.vehicle.service;

import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.model.VehicleModel;

import java.util.List;

public interface VehicleService {
    Vehicle addNewVehicle(String modelName, Long vehicleCategoryId, Long ownerId, String licensePlate, Integer issueYear);

    Vehicle getById(Long id);

    Vehicle getByLicensePlate(String licensePlate);

    List<Vehicle> getAllVehicles();

    VehicleModel getModelById(Long id);

    Vehicle update(Long id, Long ownerId, String licensePlate);
}
