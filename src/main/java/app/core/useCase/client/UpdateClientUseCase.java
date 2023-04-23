package app.core.useCase.client;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.service.ClientCategoryService;
import app.core.domain.client.dto.Client;
import app.core.domain.client.service.ClientService;
import app.core.exception.ApplicationException;
import app.core.useCase.UseCase;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateClientUseCase implements UseCase<UpdateClientUseCase.Input, UpdateClientUseCase.Output> {

    private final ClientService clientService;

    private final ClientCategoryService clientCategoryService;

    public UpdateClientUseCase(ClientService clientService, ClientCategoryService clientCategoryService) {
        this.clientService = clientService;
        this.clientCategoryService = clientCategoryService;
    }

    @Override
    @Transactional
    public Output handle(Input input) throws ApplicationException {
        ClientCategory clientCategory = clientCategoryService.getById(input.clientCategoryId);

        if (clientCategory == null)
            throw new ApplicationException("Client category with ID " + input.clientCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        Client client = clientService.getById(input.id);

        if (client == null)
            throw new ApplicationException("Client with ID " + input.clientCategoryId + " does not exist", HttpStatus.BAD_REQUEST);

        if (clientService.getByPhone(input.phone) != null)
            throw new ApplicationException("Client with phone " + input.phone + " is already assigned", HttpStatus.BAD_REQUEST);

        client = clientService.update(
                client.id(),
                input.firstName,
                input.lastName,
                input.phone,
                clientCategory
        );

        return new Output(client);
    }

    public record Input(
            @NotNull
            Long id,
            @NotNull
            String firstName,
            @NotNull
            String lastName,
            @NotNull
            String phone,
            @NotNull
            Long clientCategoryId
    ) {
    }

    public record Output(Client client) {
    }
}
