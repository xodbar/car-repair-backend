package app.core.domain.vehicle.service;

import app.core.domain.vehicle.dto.Vehicle;

interface VehicleService {
    Vehicle addNewVehicle(String modelName, Long vehicleCategoryId, Long ownerId, String licensePlate, Integer issueYear);

    Vehicle getById(Long id);

    Vehicle getByLicensePlate(String licensePlate);

    Vehicle update(Long id, Long ownerId, String licensePlate);
}
