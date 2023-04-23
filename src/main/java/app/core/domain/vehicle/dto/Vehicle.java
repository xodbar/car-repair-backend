package app.core.domain.vehicle.dto;

import app.core.domain.client.dto.LightweightClient;
import app.core.domain.vehicle.category.VehicleCategory;

import java.io.Serializable;

public record Vehicle(
        Long id,
        String modelName,
        VehicleCategory vehicleCategory,
        LightweightClient owner,
        String licensePlate,
        Integer issueYear
) implements Serializable {
}
