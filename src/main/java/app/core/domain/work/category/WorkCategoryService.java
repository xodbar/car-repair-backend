package app.core.domain.work.category;

import org.springframework.stereotype.Service;

@Service
public class WorkCategoryService {

    private final WorkCategoryRepository workCategoryRepository;

    public WorkCategoryService(WorkCategoryRepository workCategoryRepository) {
        this.workCategoryRepository = workCategoryRepository;
    }

    public WorkCategory createCategory(String name) {
        return workCategoryRepository.save(new WorkCategoryModel(-1L, name)).toDto();
    }

    public WorkCategory getCategoryByName(String name) {
        return workCategoryRepository.findByName(name).toDto();
    }

    public WorkCategory getCategory(Long id) {
        return workCategoryRepository.findById(id).orElseThrow().toDto();
    }
}
