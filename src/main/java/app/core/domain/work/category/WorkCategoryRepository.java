package app.core.domain.work.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategoryModel, Long> {
    WorkCategoryModel findByName(String name);
}
