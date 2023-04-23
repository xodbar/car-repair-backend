package app.web.controller.vehicle;

import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.vehicle.service.VehicleService;
import app.core.exception.ApplicationException;
import app.core.useCase.vehicle.AddVehicleUseCase;
import app.core.useCase.vehicle.UpdateVehicleUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final AddVehicleUseCase addVehicleUseCase;

    private final UpdateVehicleUseCase updateVehicleUseCase;

    private final VehicleService vehicleService;

    public VehicleController(AddVehicleUseCase addVehicleUseCase, UpdateVehicleUseCase updateVehicleUseCase, VehicleService vehicleService) {
        this.addVehicleUseCase = addVehicleUseCase;
        this.updateVehicleUseCase = updateVehicleUseCase;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @ResponseBody
    public AddVehicleUseCase.Output addVehicle(@RequestBody AddVehicleUseCase.Input body) throws ApplicationException {
        return addVehicleUseCase.handle(body);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Vehicle getVehicle(@PathVariable("id") Long id) {
        return vehicleService.getById(id);
    }

    @GetMapping
    @ResponseBody
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PutMapping
    @ResponseBody
    public UpdateVehicleUseCase.Output updateVehicle(@RequestBody UpdateVehicleUseCase.Input body) throws ApplicationException {
        return updateVehicleUseCase.handle(body);
    }
}
