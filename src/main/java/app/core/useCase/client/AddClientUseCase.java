package app.core.useCase.client;

import app.core.domain.client.category.service.ClientCategoryService;
import app.core.domain.client.dto.Client;
import app.core.domain.client.service.ClientService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Component
public class AddClientUseCase implements UseCase<AddClientUseCase.Input, AddClientUseCase.Output> {

    private final ClientService clientService;

    private final ClientCategoryService clientCategoryService;

    public AddClientUseCase(ClientService clientService, ClientCategoryService clientCategoryService) {
        this.clientService = clientService;
        this.clientCategoryService = clientCategoryService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        if (clientCategoryService.getById(input.clientCategoryId) == null)
            throw new ApplicationException("Client category with ID " + input.clientCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (clientService.getByPhone(input.phone) != null)
            throw new ApplicationException("User with phone " + input.phone + " already exists", HttpStatus.BAD_REQUEST);

        Client newClient = clientService.addNewClient(
                input.firstName,
                input.lastName,
                input.phone,
                input.clientCategoryId
        );

        return new Output(newClient);
    }

    public record Input(
            @NotNull
            String firstName,
            @NotNull
            String lastName,
            @NotNull
            String phone,
            @NotNull
            Long clientCategoryId
    ) implements Serializable {
    }

    public record Output(
            Client client
    ) implements Serializable {
    }
}
