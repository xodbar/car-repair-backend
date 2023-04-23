package app.core.domain.client.category.repo;

import app.core.domain.client.category.model.ClientCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCategoryRepository extends JpaRepository<ClientCategoryModel, Long> {
    ClientCategoryModel findByName(String name);
}
