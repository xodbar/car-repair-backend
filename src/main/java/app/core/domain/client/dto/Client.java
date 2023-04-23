package app.core.domain.client.dto;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.vehicle.dto.Vehicle;

import java.io.Serializable;
import java.util.List;

public record Client(
        Long id,
        String firstName,
        String lastName,
        String phone,
        ClientCategory clientCategory,
        List<Vehicle> vehicles
) implements Serializable {
}
