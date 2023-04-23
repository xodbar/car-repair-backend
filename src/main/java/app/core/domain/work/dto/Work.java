package app.core.domain.work.dto;

import app.core.domain.employee.dto.Employee;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.work.category.dto.WorkCategory;

import java.io.Serializable;
import java.time.LocalDate;

public record Work(
        Long id,
        WorkCategory workCategory,
        Vehicle vehicle,
        Employee employee,
        Integer totalPrice,
        LocalDate plannedEndDate,
        LocalDate actualEndDate
) implements Serializable {
}
