package org.example;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private boolean compare(Weight thatWeight) {

        double thisBase = this.convertToBaseUnit();
        double thatBase = thatWeight.convertToBaseUnit();

        return Double.compare(thisBase, thatBase) == 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Weight))
            return false;

        Weight other = (Weight) o;

        return compare(other);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = convertToBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight thatWeight) {
        return addAndConvert(thatWeight, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addAndConvert(weight, targetUnit);
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit) {

        if (weight == null)
            throw new IllegalArgumentException("Weight cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = weight.convertToBaseUnit();

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Weight(result, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}
