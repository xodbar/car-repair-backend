package app.core.domain.work.category.service;

import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.model.WorkCategoryModel;
import app.core.domain.work.category.repo.WorkCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkCategoryServiceImpl implements WorkCategoryService {

    private final WorkCategoryRepository workCategoryRepository;

    public WorkCategoryServiceImpl(WorkCategoryRepository workCategoryRepository) {
        this.workCategoryRepository = workCategoryRepository;
    }

    @Override
    public WorkCategory createCategory(String name) {
        return workCategoryRepository.save(new WorkCategoryModel(-1L, name)).toDto();
    }

    @Override
    public WorkCategory getByName(String name) {
        if (workCategoryRepository.findByName(name) != null)
            return workCategoryRepository.findByName(name).toDto();

        return null;
    }

    @Override
    public WorkCategory getById(Long id) {
        if (workCategoryRepository.findById(id).isPresent())
            return workCategoryRepository.findById(id).get().toDto();

        return null;
    }

    @Override
    public WorkCategoryModel getModelById(Long id) {
        return workCategoryRepository.findById(id).orElseThrow();
    }
}
