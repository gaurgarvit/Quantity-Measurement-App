package org.example;

public class Length {

    private double value;
    private LengthUnit unit;

    // tolerance for floating point comparison
    private static final double EPSILON = 0.0001;

    // Enum with conversion factors (base unit = inches)
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
        this.value = value;
        this.unit = unit;
    }

    // Convert value to base unit (inches)
    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Compare two Length objects
    public boolean compare(Length thatLength) {

        if (thatLength == null)
            return false;

        double thisValue = this.convertToBaseUnit();
        double thatValue = thatLength.convertToBaseUnit();

        return Math.abs(thisValue - thatValue) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Length that = (Length) o;

        return compare(that);
    }

    // Optional main method for quick testing
    public static void main(String[] args) {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + length1.equals(length2));

        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + length3.equals(length4));

        Length length5 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length length6 = new Length(1.0, LengthUnit.FEET);

        System.out.println("Are lengths equal? " + length5.equals(length6));
    }
}