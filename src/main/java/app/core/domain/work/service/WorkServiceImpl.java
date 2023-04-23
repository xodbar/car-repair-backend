package app.core.domain.work.service;

import app.core.domain.employee.model.EmployeeModel;
import app.core.domain.employee.service.EmployeeService;
import app.core.domain.vehicle.model.VehicleModel;
import app.core.domain.vehicle.service.VehicleService;
import app.core.domain.work.category.model.WorkCategoryModel;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.domain.work.dto.Work;
import app.core.domain.work.model.WorkModel;
import app.core.domain.work.repo.WorkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;

    private final EmployeeService employeeService;

    private final VehicleService vehicleService;

    private final WorkCategoryService workCategoryService;

    public WorkServiceImpl(WorkRepository workRepository, EmployeeService employeeService, VehicleService vehicleService, WorkCategoryService workCategoryService) {
        this.workRepository = workRepository;
        this.employeeService = employeeService;
        this.vehicleService = vehicleService;
        this.workCategoryService = workCategoryService;
    }

    @Override
    public Work registerNewWork(Long workCategoryId, Long vehicleId, Long employeeId, Integer totalPrice, LocalDate plannedEndDate) {
        WorkCategoryModel workCategoryModel = workCategoryService.getModelById(workCategoryId);
        VehicleModel vehicleModel = vehicleService.getModelById(vehicleId);
        EmployeeModel employeeModel = employeeService.getModelById(employeeId);

        return workRepository.save(new WorkModel(
                -1L,
                workCategoryModel,
                vehicleModel,
                employeeModel,
                totalPrice,
                plannedEndDate
        )).toDto();
    }

    @Override
    public Work getById(Long id) {
        if (workRepository.findById(id).isPresent())
            return workRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public List<Work> getAll() {
        return workRepository.findAll()
                .stream()
                .map(WorkModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Work> getAllByEmployee(Long employeeId) {
        return workRepository.findAllByEmployeeId(employeeId)
                .stream()
                .map(WorkModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Work> getAllByVehicleCategory(Long vehicleCategoryId) {
        return workRepository.findAllByVehicleVehicleCategoryId(vehicleCategoryId)
                .stream()
                .map(WorkModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Work> getAllByWorkCategory(Long workCategoryId) {
        return workRepository.findAllByWorkCategoryId(workCategoryId)
                .stream()
                .map(WorkModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Work update(Long id, Long workCategoryId, Long vehicleId, Long employeeId, Integer totalPrice, LocalDate plannedEndDate, LocalDate actualEndTime) {
        if (getById(id) == null)
            return null;

        WorkModel workModel = workRepository.findById(id).orElseThrow();

        WorkCategoryModel workCategoryModel = workCategoryId == null ? null :
                workCategoryService.getModelById(workCategoryId);

        VehicleModel vehicleModel = vehicleId == null ? null :
                vehicleService.getModelById(vehicleId);

        EmployeeModel employeeModel = employeeId == null ? null :
                employeeService.getModelById(employeeId);

        if (workCategoryModel != null)
            workModel.setWorkCategory(workCategoryModel);

        if (vehicleModel != null)
            workModel.setVehicle(vehicleModel);

        if (employeeModel != null)
            workModel.setEmployee(employeeModel);

        if (totalPrice != null)
            workModel.setTotalPrice(totalPrice);

        if (plannedEndDate != null)
            workModel.setPlannedEndDate(plannedEndDate);

        if (actualEndTime != null)
            workModel.setActualEndDate(actualEndTime);

        return workRepository.save(workModel).toDto();
    }
}
