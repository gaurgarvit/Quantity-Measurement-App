package org.example.service;


import org.example.Entity.QuantityMeasurementEntity;
import org.example.dto.QuantityDTO;
import org.example.exception.QuantityMeasurementException;
import org.example.model.LengthUnit;
import org.example.model.Quantity;
import org.example.model.Unit;
import org.example.model.WeightUnit;
import org.example.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementService implements IQuantityMeasurementService {
    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

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

        try {
            Unit u1 = getUnit(q1.getUnitName(), q1.getMeasurementType());
            Unit u2 = getUnit(q2.getUnitName(), q2.getMeasurementType());

            if (!u1.getMeasurementType().equals(u2.getMeasurementType())) {
                throw new QuantityMeasurementException("Cannot add different types");
            }

            double resultBase = u1.toBase(q1.getValue()) + u2.toBase(q2.getValue());
            double result = u1.fromBase(resultBase);

            QuantityDTO resultDTO =
                    new QuantityDTO(result, u1.getUnitName(), u1.getMeasurementType());

            // 🔥 SAVE TO REPOSITORY
            repository.save(new QuantityMeasurementEntity(
                    "ADD",
                    q1.getValue() + " " + q1.getUnitName(),
                    q2.getValue() + " " + q2.getUnitName(),
                    resultDTO.getValue() + " " + resultDTO.getUnitName()
            ));

            return resultDTO;

        } catch (Exception e) {

            repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));

            throw e;
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity m = toModel(q);
        Unit target = getUnit(targetUnit, q.getMeasurementType());

        double base = m.toBase();
        double converted = target.fromBase(base);

        return new QuantityDTO(
                converted,
                target.getUnitName(),
                target.getMeasurementType()
        );
    }
}
