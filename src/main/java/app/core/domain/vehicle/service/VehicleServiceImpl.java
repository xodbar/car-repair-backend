package app.core.domain.vehicle.service;

import app.core.domain.client.model.ClientModel;
import app.core.domain.client.service.ClientService;
import app.core.domain.vehicle.category.VehicleCategoryModel;
import app.core.domain.vehicle.category.VehicleCategoryService;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.model.VehicleModel;
import app.core.domain.vehicle.repo.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final VehicleCategoryService vehicleCategoryService;

    private final ClientService clientService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleCategoryService vehicleCategoryService, ClientService clientService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleCategoryService = vehicleCategoryService;
        this.clientService = clientService;
    }

    @Override
    public Vehicle addNewVehicle(String modelName, Long vehicleCategoryId, Long ownerId, String licensePlate, Integer issueYear) {
        ClientModel clientModel = clientService.getModelById(ownerId);
        VehicleCategoryModel vehicleCategoryModel = vehicleCategoryService.getModelById(vehicleCategoryId);

        return vehicleRepository.save(new VehicleModel(
                -1L,
                modelName,
                vehicleCategoryModel,
                clientModel,
                licensePlate,
                issueYear
        )).toDto();
    }

    @Override
    public Vehicle getById(Long id) {
        if (vehicleRepository.findById(id).isPresent())
            return vehicleRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public Vehicle getByLicensePlate(String licensePlate) {
        if (vehicleRepository.findByLicensePlate(licensePlate) != null)
            return vehicleRepository.findByLicensePlate(licensePlate).toDto();

        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleModel::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleModel getModelById(Long id) {
        return vehicleRepository.findById(id).orElseThrow();
    }

    @Override
    public Vehicle update(Long id, Long ownerId, String licensePlate) {
        VehicleModel vehicleModel = vehicleRepository.getReferenceById(id);

        if (ownerId != null)
            vehicleModel.setOwner(clientService.getModelById(id));

        if (licensePlate != null && !licensePlate.isEmpty())
            vehicleModel.setLicensePlate(licensePlate);

        return vehicleRepository.save(vehicleModel).toDto();
    }
}
