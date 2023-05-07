package app.core.useCase.work.category;

import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AddWorkCategoryUseCase implements UseCase<AddWorkCategoryUseCase.Input, AddWorkCategoryUseCase.Output> {

    private final WorkCategoryService workCategoryService;

    public AddWorkCategoryUseCase(WorkCategoryService workCategoryService) {
        this.workCategoryService = workCategoryService;
    }

    @Override
    public Output handle(Input input) throws ApplicationException {
        if (workCategoryService.getByName(input.name) != null)
            throw new ApplicationException("Work category with name " + input.name + " is already exists", HttpStatus.BAD_REQUEST);

        return new Output(workCategoryService.createCategory(input.name));
    }

    public record Input(
            String name
    ) {
    }

    public record Output(
            WorkCategory workCategory
    ) {
    }
}
