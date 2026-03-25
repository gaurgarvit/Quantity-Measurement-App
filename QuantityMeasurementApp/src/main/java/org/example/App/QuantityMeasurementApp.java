package org.example.App;

import org.example.controller.QuantityMeasurementController;
import org.example.dto.QuantityDTO;
import org.example.service.IQuantityMeasurementService;
import org.example.service.QuantityMeasurementService;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementService service = new QuantityMeasurementService();
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "Length");

        controller.performComparison(q1, q2);
        controller.performAddition(q1, q2);
        controller.performConversion(q1, "INCHES");
    }
}
