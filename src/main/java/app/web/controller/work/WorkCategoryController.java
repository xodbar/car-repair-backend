package app.web.controller.work;

import app.core.domain.employee.dto.Employee;
import app.core.domain.work.category.dto.WorkCategory;
import app.core.domain.work.category.service.WorkCategoryService;
import app.core.exception.ApplicationException;
import app.core.useCase.work.category.AddWorkCategoryUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-category")
public class WorkCategoryController {

    private final AddWorkCategoryUseCase addWorkCategoryUseCase;

    private final WorkCategoryService workCategoryService;

    public WorkCategoryController(AddWorkCategoryUseCase addWorkCategoryUseCase, WorkCategoryService workCategoryService) {
        this.addWorkCategoryUseCase = addWorkCategoryUseCase;
        this.workCategoryService = workCategoryService;
    }

    @PostMapping
    @ResponseBody
    public AddWorkCategoryUseCase.Output addWorkCategory(@RequestBody AddWorkCategoryUseCase.Input input) throws ApplicationException {
        return addWorkCategoryUseCase.handle(input);
    }

    @GetMapping
    @ResponseBody
    public List<WorkCategory> getAllWorkCategories() {
        return workCategoryService.getAll();
    }

    @GetMapping("/{categoryName}/employees")
    @ResponseBody
    public List<Employee> getAllSpecializedEmployees(@PathVariable String categoryName) {
        return workCategoryService.getAllSpecializedEmployees(categoryName);
    }
}
