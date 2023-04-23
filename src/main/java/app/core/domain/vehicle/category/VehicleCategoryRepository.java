package app.core.domain.vehicle.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VehicleCategoryRepository extends JpaRepository<VehicleCategoryModel, Long> {
    VehicleCategoryModel findByName(String name);
}
