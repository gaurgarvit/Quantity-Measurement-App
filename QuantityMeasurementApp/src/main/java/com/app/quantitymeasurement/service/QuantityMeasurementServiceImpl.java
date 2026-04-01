package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.entity.OperationHistory;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.OperationHistoryRepository;
import com.app.quantitymeasurement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private OperationHistoryRepository repository;

    @Autowired
    private UserRepository userRepository;


    private double toBase(String unit, double value) {
        switch (unit.toUpperCase()) {
            // Length (Base: INCH)
            case "INCH": return value;
            case "FEET": return value * 12.0;
            case "YARD": return value * 36.0;
            case "MILE": return value * 63360.0;
            case "CENTIMETER": return value / 2.54;
            case "METER": return value / 0.0254;
            case "KILOMETER": return value / 0.0000254;

            // Volume (Base: LITRE)
            case "LITRE": return value;
            case "GALLON": return value * 3.78541;
            case "MILLILITRE": return value / 1000.0;

            // Weight (Base: GRAM)
            case "GRAM": return value;
            case "KILOGRAM": return value * 1000.0;
            case "TONNE": return value * 1000000.0;

            // Temperature (Base: CELSIUS)
            case "CELSIUS": return value;
            case "FAHRENHEIT": return (value - 32) * 5.0 / 9.0;
            case "KELVIN": return value - 273.15;

            default: throw new QuantityMeasurementException("Invalid unit: " + unit);
        }
    }

    private double fromBase(String unit, double baseValue) {
        switch (unit.toUpperCase()) {
            // Length (Base: INCH)
            case "INCH": return baseValue;
            case "FEET": return baseValue / 12.0;
            case "YARD": return baseValue / 36.0;
            case "MILE": return baseValue / 63360.0;
            case "CENTIMETER": return baseValue * 2.54;
            case "METER": return baseValue * 0.0254;
            case "KILOMETER": return baseValue * 0.0000254;

            // Volume (Base: LITRE)
            case "LITRE": return baseValue;
            case "GALLON": return baseValue / 3.78541;
            case "MILLILITRE": return baseValue * 1000.0;

            // Weight (Base: GRAM)
            case "GRAM": return baseValue;
            case "KILOGRAM": return baseValue / 1000.0;
            case "TONNE": return baseValue / 1000000.0;

            // Temperature (Base: CELSIUS)
            case "CELSIUS": return baseValue;
            case "FAHRENHEIT": return (baseValue * 9.0 / 5.0) + 32;
            case "KELVIN": return baseValue + 273.15;

            default: throw new QuantityMeasurementException("Invalid unit: " + unit);
        }
    }

    private void save(String operation, QuantityInputDTO input,
                      QuantityMeasurementDTO dto, boolean error) {

        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            username = auth.getName();
        }

        User user = null;
        if (username != null && !username.equals("anonymousUser")) {
            user = userRepository.findByUsername(username).orElse(null);
        }

        OperationHistory history = new OperationHistory();
        history.setOperation(operation);
        history.setMeasurementType(input.getThisMeasurementType());
        
        history.setInputValue1(input.getThisValue());
        history.setInputUnit1(input.getThisUnit());
        
        if (operation.equals("COMPARE") || operation.equals("ADD") || operation.equals("SUBTRACT") || operation.equals("DIVIDE")) {
            history.setInputValue2(input.getThatValue());
            history.setInputUnit2(input.getThatUnit());
        }

        history.setResultValue(dto.getResultValue());
        history.setResultUnit(dto.getResultUnit());
        history.setError(error);
        history.setUser(user);

        repository.save(history);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        boolean areEqual = Math.abs(v1 - v2) < 0.0001;
        dto.setResultString(String.valueOf(areEqual));
        dto.setResultValue(areEqual ? 1.0 : 0.0);
        dto.setResultUnit("BOOLEAN");
        dto.setError(false);

        save("COMPARE", input, dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double baseValue = toBase(input.getThisUnit(), input.getThisValue());
        double result = fromBase(input.getTargetUnit(), baseValue);

        dto.setResultValue(result);
        dto.setResultUnit(input.getTargetUnit());
        dto.setResultString(String.format("%.4f %s", result, input.getTargetUnit()));
        dto.setError(false);

        save("CONVERT", input, dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        double resultBase = v1 + v2;
        double result = fromBase(input.getTargetUnit(), resultBase);

        dto.setResultValue(result);
        dto.setResultUnit(input.getTargetUnit());
        dto.setResultString(String.format("%.4f %s", result, input.getTargetUnit()));
        dto.setError(false);

        save("ADD", input, dto, false);
        return dto;
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        double v1 = toBase(input.getThisUnit(), input.getThisValue());
        double v2 = toBase(input.getThatUnit(), input.getThatValue());

        double resultBase = v1 - v2;
        double result = fromBase(input.getTargetUnit(), resultBase);

        dto.setResultValue(result);
        dto.setResultUnit(input.getTargetUnit());
        dto.setResultString(String.format("%.4f %s", result, input.getTargetUnit()));
        dto.setError(false);

        save("SUBTRACT", input, dto, false);
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

        double resultBase = v1 / v2;
        double result = fromBase(input.getTargetUnit(), resultBase);

        dto.setResultValue(result);
        dto.setResultUnit(input.getTargetUnit());
        dto.setResultString(String.format("%.4f %s", result, input.getTargetUnit()));
        dto.setError(false);

        save("DIVIDE", input, dto, false);
        return dto;
    }

    // HISTORY APIs

    public List<OperationHistory> getByOperation(String op) {
        return repository.findByOperationIgnoreCase(op);
    }

    public List<OperationHistory> getByType(String type) {
        return repository.findByMeasurementTypeIgnoreCase(type);
    }

    public List<OperationHistory> getErrored() {
        return repository.findByErrorTrue();
    }

    public long count(String op) {
        return repository.countByOperationIgnoreCase(op);
    }
}
