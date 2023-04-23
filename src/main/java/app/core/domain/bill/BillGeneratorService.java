package app.core.domain.bill;

import app.core.domain.client.dto.LightweightClient;
import app.core.domain.employee.dto.Employee;
import app.core.domain.vehicle.dto.Vehicle;
import app.core.domain.work.dto.Work;
import app.core.useCase.work.RegisterWorkUseCase;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class BillGeneratorService {

    public void generateBill(
            Work work,
            LightweightClient client,
            Employee employee,
            Vehicle vehicle,
            RegisterWorkUseCase.Input initialRequest,
            GenerateBillPrices billPrices
    ) {
        try {
            String fileName = "work_bill_" + work.id() + ".txt";
            File file = new File(fileName);
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(fileName);

                writer.write("Client full name: " + client.firstName() + " " + client.lastName() + "\n");
                writer.write("Client contact: " + client.phone() + "\n");
                writer.write("Vehicle: " + vehicle.modelName() + ", " + vehicle.licensePlate() + "\n");
                writer.write("Implementor: " + employee.firstName() + " " + employee.lastName() + ", " + employee.employeeGrade().name() + " \n");
                writer.write("Planned end date: " + work.plannedEndDate().toString() + "\n");

                writer.write("-----------------------" + "\n");

                writer.write("Initial price: " + initialRequest.initialPrice() + "T\n");
                writer.write("Work category mismatch: " + billPrices.workCategoryMismatch + "T (10%)\n");
                writer.write("Vehicle category mismatch: " + billPrices.vehicleCategoryMismatch + "T (10%)\n");
                writer.write("Vehicle category cost: " + billPrices.vehicleCategoryCost + "T (" + vehicle.vehicleCategory().additionalCost() + "%)\n");
                writer.write("Employee grade cost: " + billPrices.employeeGradeCost + "T (" + employee.employeeGrade().getAdditionalGradePercent() + "%)\n");
                writer.write("Client category discount: -" + billPrices.clientCategoryDiscount + "T (-" + client.clientCategory().discount() + "%)\n");

                writer.write("-----------------------" + "\n");

                writer.write("Total cost: " + work.totalPrice());

                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public record GenerateBillPrices(
            Integer workCategoryMismatch,
            Integer vehicleCategoryMismatch,
            Integer employeeGradeCost,
            Integer vehicleCategoryCost,
            Integer clientCategoryDiscount
    ) {
    }
}
