package app.core.domain.employee.repo;

import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.employee.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    List<EmployeeModel> findAllByEmployeeGrade(EmployeeGrade employeeGrade);

    List<EmployeeModel> findAllBySpecializedWorkCategoryId(Long specializedWorkCategoryId);

    List<EmployeeModel> findAllBySpecializedVehicleCategoryId(Long specializedVehicleCategoryId);

    List<EmployeeModel> findAllByIsActiveTrue();
}
