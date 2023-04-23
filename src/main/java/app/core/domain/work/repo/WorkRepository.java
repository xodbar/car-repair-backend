package app.core.domain.work.repo;

import app.core.domain.work.model.WorkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkModel, Long> {
    List<WorkModel> findAllByEmployeeId(Long employeeId);

    List<WorkModel> findAllByVehicleVehicleCategoryId(Long vehicleCategoryId);

    List<WorkModel> findAllByWorkCategoryId(Long workCategoryId);
}
