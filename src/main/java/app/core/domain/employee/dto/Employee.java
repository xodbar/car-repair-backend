package app.core.domain.employee.dto;

import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.vehicle.category.VehicleCategory;
import app.core.domain.work.category.dto.WorkCategory;

import java.io.Serializable;

public record Employee(
        Long id,
        String firstName,
        String lastName,
        String avatarUrl,
        EmployeeGrade employeeGrade,
        WorkCategory specializedWorkCategory,
        VehicleCategory specializedVehicleCategory,
        Boolean isActive
) implements Serializable {
}
