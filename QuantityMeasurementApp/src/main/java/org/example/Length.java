package org.example;

public class Length {

    private double value;
    private LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {

        // Convert current value to base unit using enum logic
        return unit.convertToBaseUnit(value);
    }

    private boolean compare(Length thatLength) {

        double thisBase = this.convertToBaseUnit();
        double thatBase = thatLength.convertToBaseUnit();

        return Math.abs(thisBase - thatBase) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Length)) return false;

        Length other = (Length) o;

        return compare(other);
    }

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length thatLength) {

        return addAndConvert(thatLength, this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        return addAndConvert(length, targetUnit);
    }

    private Length addAndConvert(Length length, LengthUnit targetUnit) {

        if (length == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        double base1 = this.convertToBaseUnit();
        double base2 = length.convertToBaseUnit();

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Length(result, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}