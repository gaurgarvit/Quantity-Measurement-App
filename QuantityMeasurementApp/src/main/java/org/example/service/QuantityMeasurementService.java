package org.example.service;


import org.example.dto.QuantityDTO;
import org.example.exception.QuantityMeasurementException;
import org.example.model.LengthUnit;
import org.example.model.Quantity;
import org.example.model.Unit;
import org.example.model.WeightUnit;

public class QuantityMeasurementService implements IQuantityMeasurementService {

    private Unit getUnit(String unitName, String type) {

        switch (type.toLowerCase()) {

            case "length":
            case "lengthunit":
                return LengthUnit.valueOf(unitName);

            case "weight":
            case "weightunit":
                return WeightUnit.valueOf(unitName);

            default:
                throw new QuantityMeasurementException("Invalid type: " + type);
        }
    }

    private Quantity toModel(QuantityDTO dto) {
        Unit unit = getUnit(dto.getUnitName(), dto.getMeasurementType());
        return new Quantity(dto.getValue(), unit);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        Quantity m1 = toModel(q1);
        Quantity m2 = toModel(q2);

        if (!m1.getUnit().getMeasurementType().equals(m2.getUnit().getMeasurementType()))
            throw new QuantityMeasurementException("Different measurement types");

        return Double.compare(m1.toBase(), m2.toBase()) == 0;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        Quantity m1 = toModel(q1);
        Quantity m2 = toModel(q2);

        if (!m1.getUnit().getMeasurementType().equals(m2.getUnit().getMeasurementType()))
            throw new QuantityMeasurementException("Cannot add different categories");

        double result = m1.toBase() + m2.toBase();

        return new QuantityDTO(result,
                m1.getUnit().getUnitName(),
                m1.getUnit().getMeasurementType());
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {
        Quantity m = toModel(q);
        Unit target = getUnit(targetUnit, q.getMeasurementType());

        double base = m.toBase();
        double converted = base / target.getConversionFactor();

        return new QuantityDTO(converted,
                target.getUnitName(),
                target.getMeasurementType());
    }
}
