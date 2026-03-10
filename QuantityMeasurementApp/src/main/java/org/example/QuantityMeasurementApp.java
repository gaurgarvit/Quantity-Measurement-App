package org.example;

public class QuantityMeasurementApp {

    // Demonstrate equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {

        if (length1.equals(length2)) {
            System.out.println("The two length measurements are equal.");
            return true;
        }

        System.out.println("The two length measurements are not equal.");
        return false;
    }

    // Demonstrate comparison using values
    public static boolean demonstrateLengthComparison(
            double value1,
            Length.LengthUnit unit1,
            double value2,
            Length.LengthUnit unit2) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        return demonstrateLengthEquality(length1, length2);
    }

    // Conversion using raw values
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit) {

        Length length = new Length(value, fromUnit);

        return length.convertTo(toUnit);
    }

    // Overloaded conversion method
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }

    // UC5 Required Static Convert API
    public static double convert(
            double value,
            Length.LengthUnit source,
            Length.LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        Length sourceLength = new Length(value, source);

        Length converted = sourceLength.convertTo(target);

        return converted.getValue();
    }

    // Main method
    public static void main(String[] args) {

        Length length1 = new Length(3, Length.LengthUnit.FEET);
        Length length2 = new Length(36, Length.LengthUnit.INCHES);

        demonstrateLengthEquality(length1, length2);

        Length converted = demonstrateLengthConversion(
                2,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.INCHES
        );

        System.out.println("Converted Length: " + converted);

        double result = convert(
                1,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES
        );

        System.out.println("1 FEET in INCHES = " + result);
    }
}