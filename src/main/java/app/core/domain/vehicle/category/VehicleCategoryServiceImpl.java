package app.core.domain.vehicle.category;

import org.springframework.stereotype.Service;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;

    public VehicleCategoryServiceImpl(VehicleCategoryRepository vehicleCategoryRepository) {
        this.vehicleCategoryRepository = vehicleCategoryRepository;
    }

    @Override
    public VehicleCategory createCategory(String name, Integer additionalCost) {
        return vehicleCategoryRepository.save(new VehicleCategoryModel(-1L, name, additionalCost)).toDto();
    }

    @Override
    public VehicleCategory getByName(String name) {
        return vehicleCategoryRepository.findByName(name).toDto();
    }

    @Override
    public VehicleCategory getById(Long id) {
        return vehicleCategoryRepository.findById(id).orElseThrow().toDto();
    }

    @Override
    public VehicleCategoryModel getModelById(Long id) {
        return vehicleCategoryRepository.findById(id).orElseThrow();
    }
}
