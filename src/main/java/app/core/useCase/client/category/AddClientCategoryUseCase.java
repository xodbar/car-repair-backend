package app.core.useCase.client.category;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.service.ClientCategoryService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AddClientCategoryUseCase implements UseCase<AddClientCategoryUseCase.Input, AddClientCategoryUseCase.Output> {

    private final ClientCategoryService clientCategoryService;

    public AddClientCategoryUseCase(ClientCategoryService clientCategoryService) {
        this.clientCategoryService = clientCategoryService;
    }

    @Override
    public Output handle(Input input) throws ApplicationException {
        if (clientCategoryService.getByName(input.name) != null)
            throw new ApplicationException("Client category with name " + input.name + "already exists", HttpStatus.BAD_REQUEST);

        if (input.discountInPercents < 0)
            throw new ApplicationException("Discount must be greater than zero", HttpStatus.BAD_REQUEST);

        return new Output(clientCategoryService.createCategory(input.name, input.discountInPercents));
    }

    public record Input(
            String name,
            Integer discountInPercents
    ) {
    }

    public record Output(
            ClientCategory clientCategory
    ) {
    }
}
