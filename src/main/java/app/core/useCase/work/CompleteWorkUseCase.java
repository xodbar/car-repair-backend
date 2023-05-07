package app.core.useCase.work;

import app.core.domain.work.dto.Work;
import app.core.domain.work.service.WorkService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
public class CompleteWorkUseCase implements UseCase<CompleteWorkUseCase.Input, CompleteWorkUseCase.Output> {

    private final WorkService workService;

    public CompleteWorkUseCase(WorkService workService) {
        this.workService = workService;
    }

    @Override
    public Output handle(Input input) throws ApplicationException {
        Work work = workService.getById(input.workId);

        if (work == null)
            throw new ApplicationException("Work with ID " + input.workId + " does not exist", HttpStatus.BAD_REQUEST);

        work = workService.update(
                work.id(),
                null,
                null,
                null,
                null,
                null,
                input.actualEndDate
        );

        return new Output(work);
    }

    public record Input(
            @NotNull
            Long workId,
            @NotNull
            LocalDate actualEndDate
    ) implements Serializable {
    }

    public record Output(
            Work work
    ) implements Serializable {
    }
}
