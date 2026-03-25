package org.example.App;

import org.example.controller.QuantityMeasurementController;
import org.example.dto.QuantityDTO;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.service.IQuantityMeasurementService;
import org.example.service.QuantityMeasurementService;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementService(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "Length");

        controller.performAddition(q1, q2);

        System.out.println("\n--- History ---");
        repository.getAll().forEach(System.out::println);
    }
}