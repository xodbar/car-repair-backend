package app.core.domain.employee.service;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.employee.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(
            String firstName,
            String lastName,
            String avatarUrl,
            EmployeeGrade employeeGrade,
            Long specializedWorkCategoryId,
            Long specializedVehicleCategoryId
    );

    Employee getById(Long id);

    EmployeeModel getModelById(Long id);

    List<Employee> getAllByGrade(EmployeeGrade employeeGrade);

    List<Employee> getAllByWorkCategory(Long specializedWorkCategoryId);

    List<Employee> getAllByVehicleCategory(Long specializedVehicleCategoryId);

    List<Employee> getAllActive();

    List<Employee> getAll();

    Employee update(
            Long id,
            String firstName,
            String lastName,
            String avatarUrl,
            EmployeeGrade employeeGrade,
            Long specializedWorkCategoryId,
            Long specializedVehicleCategoryId
    );
}
