package app.core.domain.vehicle.category;

import java.io.Serializable;

public record VehicleCategory(
        Long id,
        String name,
        Integer additionalCost
) implements Serializable {
}
