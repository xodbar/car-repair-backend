package app.web.controller.employee;

import app.core.domain.employee.dto.Employee;
import app.core.domain.employee.service.EmployeeService;
import app.core.exception.ApplicationException;
import app.core.useCase.employee.AddEmployeeUseCase;
import app.core.useCase.employee.UpdateEmployeeUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final AddEmployeeUseCase addEmployeeUseCase;

    private final UpdateEmployeeUseCase updateEmployeeUseCase;

    private final EmployeeService employeeService;

    public EmployeeController(AddEmployeeUseCase addEmployeeUseCase, UpdateEmployeeUseCase updateEmployeeUseCase, EmployeeService employeeService) {
        this.addEmployeeUseCase = addEmployeeUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseBody
    public AddEmployeeUseCase.Output addNewEmployee(@RequestBody AddEmployeeUseCase.Input body) throws ApplicationException {
        return addEmployeeUseCase.handle(body);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }

    @GetMapping
    @ResponseBody
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @PutMapping
    @ResponseBody
    public UpdateEmployeeUseCase.Output updateEmployee(@RequestBody UpdateEmployeeUseCase.Input body) throws ApplicationException {
        return updateEmployeeUseCase.handle(body);
    }
}
