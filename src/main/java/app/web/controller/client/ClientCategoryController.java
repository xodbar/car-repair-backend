package app.web.controller.client;

import app.core.domain.client.category.dto.ClientCategory;
import app.core.domain.client.category.service.ClientCategoryService;
import app.core.domain.client.dto.Client;
import app.core.exception.ApplicationException;
import app.core.useCase.client.category.AddClientCategoryUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client-category")
public class ClientCategoryController {

    private final AddClientCategoryUseCase addClientCategoryUseCase;

    private final ClientCategoryService clientCategoryService;

    public ClientCategoryController(AddClientCategoryUseCase addClientCategoryUseCase, ClientCategoryService clientCategoryService) {
        this.addClientCategoryUseCase = addClientCategoryUseCase;
        this.clientCategoryService = clientCategoryService;
    }

    @PostMapping
    @ResponseBody
    public AddClientCategoryUseCase.Output createCategory(
            @RequestBody AddClientCategoryUseCase.Input input
    ) throws ApplicationException {
        return addClientCategoryUseCase.handle(input);
    }

    @GetMapping
    @ResponseBody
    public List<ClientCategory> getAllClientCategories() {
        return clientCategoryService.getAll();
    }

    @GetMapping("/{categoryName}")
    @ResponseBody
    public List<Client> getClientsOfCategory(@PathVariable("categoryName") String categoryName) {
        return clientCategoryService.getClientsByName(categoryName);
    }
}
