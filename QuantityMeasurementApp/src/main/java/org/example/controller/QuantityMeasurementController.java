package org.example.controller;

import org.example.dto.QuantityDTO;
import org.example.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {
        boolean result = service.compare(q1, q2);
        System.out.println("Comparison Result: " + result);
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {
        QuantityDTO result = service.add(q1, q2);
        System.out.println("Addition Result: " +
                result.getValue() + " " + result.getUnitName());
    }

    public void performConversion(QuantityDTO q, String targetUnit) {
        QuantityDTO result = service.convert(q, targetUnit);
        System.out.println("Converted: " +
                result.getValue() + " " + result.getUnitName());
    }
}