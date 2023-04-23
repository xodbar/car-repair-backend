package app.core.domain.work.service;

import app.core.domain.work.dto.Work;

import java.time.LocalDate;
import java.util.List;

public interface WorkService {
    Work registerNewWork(
            Long workCategoryId,
            Long vehicleId,
            Long employeeId,
            Integer totalPrice,
            LocalDate plannedEndDate
    );

    Work getById(Long id);

    List<Work> getAll();

    List<Work> getAllByEmployee(Long employeeId);

    List<Work> getAllByVehicleCategory(Long vehicleCategoryId);

    List<Work> getAllByWorkCategory(Long workCategoryId);

    Work update(
            Long id,
            Long workCategoryId,
            Long vehicleId,
            Long employeeId,
            Integer totalPrice,
            LocalDate plannedEndDate,
            LocalDate actualEndTime
    );
}
