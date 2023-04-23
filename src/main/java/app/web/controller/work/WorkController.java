package app.web.controller.work;

import app.core.domain.work.dto.Work;
import app.core.domain.work.service.WorkService;
import app.core.exception.ApplicationException;
import app.core.useCase.work.CompleteWorkUseCase;
import app.core.useCase.work.RegisterWorkUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
public class WorkController {

    private final RegisterWorkUseCase registerWorkUseCase;

    private final CompleteWorkUseCase completeWorkUseCase;

    private final WorkService workService;

    public WorkController(RegisterWorkUseCase registerWorkUseCase, CompleteWorkUseCase completeWorkUseCase, WorkService workService) {
        this.registerWorkUseCase = registerWorkUseCase;
        this.completeWorkUseCase = completeWorkUseCase;
        this.workService = workService;
    }

    @PostMapping
    @ResponseBody
    public RegisterWorkUseCase.Output registerWork(@RequestBody RegisterWorkUseCase.Input body) throws ApplicationException {
        return registerWorkUseCase.handle(body);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Work getWork(@PathVariable("id") Long id) {
        return workService.getById(id);
    }

    @GetMapping
    @ResponseBody
    public List<Work> getAllWorks() {
        return workService.getAll();
    }

    @PutMapping
    @ResponseBody
    public CompleteWorkUseCase.Output completeWork(@RequestBody CompleteWorkUseCase.Input body) throws ApplicationException {
        return completeWorkUseCase.handle(body);
    }
}
