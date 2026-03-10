package org.example;

public class Length {

    private double value;
    private LengthUnit unit;

    // Enum for units
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

    // Constructor
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

    // Getter
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to base unit (inches)
    private double convertToBaseUnit() {
        double baseValue = value * unit.getConversionFactor();
        return Math.round(baseValue * 100.0) / 100.0;
    }

    // Compare two lengths
    private boolean compare(Length thatLength) {
        if (thatLength == null) {
            return false;
        }

        double thisBase = this.convertToBaseUnit();
        double thatBase = thatLength.convertToBaseUnit();

        return Double.compare(thisBase, thatBase) == 0;
    }

    // Override equals
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Length)) {
            return false;
        }

        Length other = (Length) o;

        return compare(other);
    }

    // Convert to another unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = convertToBaseUnit();

        double convertedValue = baseValue / targetUnit.getConversionFactor();

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Length(convertedValue, targetUnit);
    }

    // toString
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}