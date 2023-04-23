package app.core.domain.employee.service;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.grade.EmployeeGrade;
import app.core.domain.employee.model.EmployeeModel;
import app.core.domain.employee.repo.EmployeeRepository;
import app.core.domain.vehicle.category.VehicleCategoryModel;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.domain.work.category.model.WorkCategoryModel;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.domain.work.category.service.WorkCategoryServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final WorkCategoryService workCategoryService;

    private final VehicleCategoryService vehicleCategoryService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, WorkCategoryServiceImpl workCategoryService, VehicleCategoryService vehicleCategoryService) {
        this.employeeRepository = employeeRepository;
        this.workCategoryService = workCategoryService;
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName, String avatarUrl, EmployeeGrade employeeGrade, Long specializedWorkCategoryId, Long specializedVehicleCategoryId) {
        WorkCategoryModel workCategoryModel = workCategoryService.getModelById(specializedWorkCategoryId);
        VehicleCategoryModel vehicleCategoryModel = vehicleCategoryService.getModelById(specializedVehicleCategoryId);

        return employeeRepository.save(new EmployeeModel(
                -1L,
                firstName,
                lastName,
                avatarUrl,
                employeeGrade,
                workCategoryModel,
                vehicleCategoryModel,
                true
        )).toDto();
    }

    @Override
    public Employee getById(Long id) {
        if (employeeRepository.findById(id).isPresent())
            return employeeRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public EmployeeModel getModelById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Employee> getAllByGrade(EmployeeGrade employeeGrade) {
        return employeeRepository.findAllByEmployeeGrade(employeeGrade)
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllByWorkCategory(Long specializedWorkCategoryId) {
        return employeeRepository.findAllBySpecializedWorkCategoryId(specializedWorkCategoryId)
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllByVehicleCategory(Long specializedVehicleCategoryId) {
        return employeeRepository.findAllBySpecializedVehicleCategoryId(specializedVehicleCategoryId)
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllActive() {
        return employeeRepository.findAllByIsActiveTrue()
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Employee update(
            Long id,
            String firstName,
            String lastName,
            String avatarUrl,
            EmployeeGrade employeeGrade,
            Long specializedWorkCategoryId,
            Long specializedVehicleCategoryId
    ) {
        EmployeeModel employeeModel = getModelById(id);

        WorkCategoryModel workCategoryModel = specializedWorkCategoryId == null ? null :
                workCategoryService.getModelById(specializedWorkCategoryId);

        VehicleCategoryModel vehicleCategoryModel = specializedVehicleCategoryId == null ? null :
                vehicleCategoryService.getModelById(specializedVehicleCategoryId);

        if (firstName != null && !firstName.isEmpty())
            employeeModel.setFirstName(firstName);

        if (lastName != null && !lastName.isEmpty())
            employeeModel.setLastName(lastName);

        if (avatarUrl != null && !avatarUrl.isEmpty())
            employeeModel.setAvatarUrl(avatarUrl);

        if (workCategoryModel != null)
            employeeModel.setSpecializedWorkCategory(workCategoryModel);

        if (vehicleCategoryModel != null)
            employeeModel.setSpecializedVehicleCategory(vehicleCategoryModel);

        if (employeeGrade != null)
            employeeModel.setEmployeeGrade(employeeGrade);

        return employeeRepository.save(employeeModel).toDto();
    }
}
