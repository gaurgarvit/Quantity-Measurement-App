package org.example;


public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit {

        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

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

        double baseValue = value * unit.getConversionFactor();

        return Math.round(baseValue * 100.0) / 100.0;
    }

    private boolean compare(Length thatLength) {

        double thisBase = this.convertToBaseUnit();
        double thatBase = thatLength.convertToBaseUnit();

        return Double.compare(thisBase, thatBase) == 0;
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

        double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);

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

        double thisBase = this.convertToBaseUnit();
        double thatBase = length.convertToBaseUnit();

        double sumBase = thisBase + thatBase;

        double result = convertFromBaseToTargetUnit(sumBase, targetUnit);

        return new Length(result, targetUnit);
    }

    private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {

        double converted = lengthInInches / targetUnit.getConversionFactor();

        return Math.round(converted * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}