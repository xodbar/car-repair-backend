package app.web.controller.client;

import app.core.domain.client.dto.Client;
import app.core.domain.client.service.ClientService;
import app.core.exception.ApplicationException;
import app.core.useCase.client.AddClientUseCase;
import app.core.useCase.client.UpdateClientUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final AddClientUseCase addClientUseCase;

    private final UpdateClientUseCase updateClientUseCase;

    private final ClientService clientService;

    public ClientController(AddClientUseCase addClientUseCase, UpdateClientUseCase updateClientUseCase, ClientService clientService) {
        this.addClientUseCase = addClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseBody
    public AddClientUseCase.Output addClient(AddClientUseCase.Input body) throws ApplicationException {
        return addClientUseCase.handle(body);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Client getClient(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }

    @GetMapping
    @ResponseBody
    public List<Client> getAllClients() {
        return clientService.getAll();
    }

    @PutMapping
    @ResponseBody
    public UpdateClientUseCase.Output updateClient(@RequestBody UpdateClientUseCase.Input body) throws ApplicationException {
        return updateClientUseCase.handle(body);
    }
}
