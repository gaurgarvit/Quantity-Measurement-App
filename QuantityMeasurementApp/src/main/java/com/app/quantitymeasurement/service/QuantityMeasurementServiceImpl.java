package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.entity.OperationHistory;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.OperationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private OperationHistoryRepository repository;


    private double toBase(String unit, double value) {
        switch (unit.toUpperCase()) {
            case "INCHES": return value;
            case "FEET": return value * 12;
            case "YARDS": return value * 36;
            case "CELSIUS": return value;
            case "FAHRENHEIT": return (value - 32) * 5 / 9;
            default: throw new QuantityMeasurementException("Invalid unit");
        }
    }

    private void save(String op, String type, QuantityMeasurementDTO dto, boolean error) {
        OperationHistory h = new OperationHistory();
        h.setOperation(op);
        h.setType(type);
        h.setError(error);
        h.setResultValue(dto.getResultValue());
        h.setResultUnit(dto.getResultUnit());
        h.setResultString(dto.getResultString());
        h.setErrorMessage(dto.getErrorMessage());
        h.setTimestamp(LocalDateTime.now());

        repository.save(h);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        dto.setResultString(String.valueOf(v1 == v2));
        dto.setError(false);

        save("COMPARE", input.getThisMeasurementType(), dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double base = toBase(input.getThisUnit(), input.getThisValue());

        double result;
        if (input.getTargetUnit().equalsIgnoreCase("FAHRENHEIT")) {
            result = (base * 9 / 5) + 32;
        } else {
            result = base;
        }

        dto.setResultValue(result);
        dto.setError(false);

        save("CONVERT", input.getThisMeasurementType(), dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        dto.setResultValue(v1 + v2);
        dto.setError(false);

        save("ADD", input.getThisMeasurementType(), dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        dto.setResultValue(v1 - v2);
        dto.setError(false);

        save("SUBTRACT", input.getThisMeasurementType(), dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityInputDTO input) {
        if (input.getThatValue() == 0) {
            throw new QuantityMeasurementException("Divide by zero");
        }

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        dto.setResultValue(v1 / v2);
        dto.setError(false);

        save("DIVIDE", input.getThisMeasurementType(), dto, false);
        return dto;
    }

    // HISTORY APIs

    public List<OperationHistory> getByOperation(String op) {
        return repository.findByOperationIgnoreCase(op);
    }

    public List<OperationHistory> getByType(String type) {
        return repository.findByTypeIgnoreCase(type);
    }

    public List<OperationHistory> getErrored() {
        return repository.findByErrorTrue();
    }

    public long count(String op) {
        return repository.countByOperationIgnoreCase(op);
    }
}
