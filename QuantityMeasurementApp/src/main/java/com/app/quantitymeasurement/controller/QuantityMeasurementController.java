package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.entity.OperationHistory;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    @PostMapping("/add")
    public QuantityMeasurementDTO add(@RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }

    @GetMapping("/history/operation/{operation}")
    public List<OperationHistory> getByOperation(@PathVariable String operation) {
        return service.getByOperation(operation);
    }

    @GetMapping("/history/type/{type}")
    public List<OperationHistory> getByType(@PathVariable String type) {
        return service.getByType(type);
    }

    @GetMapping("/history/errored")
    public List<OperationHistory> getErrored() {
        return service.getErrored();
    }

    @GetMapping("/count/{operation}")
    public long count(@PathVariable String operation) {
        return service.count(operation);
    }
}
