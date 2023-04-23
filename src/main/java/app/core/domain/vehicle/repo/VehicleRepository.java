package app.core.domain.vehicle.repo;

import app.core.domain.vehicle.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {
    VehicleModel findByLicensePlate(String licensePlate);

    List<VehicleModel> findAllByOwnerId(Long ownerId);
}
