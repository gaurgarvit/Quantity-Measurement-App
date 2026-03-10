package org.example;

public class QuantityMeasurementApp {

    // Weight Equality
    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {

        if (w1.equals(w2)) {
            System.out.println("The two weight measurements are equal.");
            return true;
        }

        System.out.println("The two weight measurements are not equal.");
        return false;
    }

    // Weight Comparison
    public static boolean demonstrateWeightComparison(
            double value1,
            WeightUnit unit1,
            double value2,
            WeightUnit unit2) {

        Weight w1 = new Weight(value1, unit1);
        Weight w2 = new Weight(value2, unit2);

        return demonstrateWeightEquality(w1, w2);
    }

    // Weight Conversion
    public static Weight demonstrateWeightConversion(
            double value,
            WeightUnit fromUnit,
            WeightUnit toUnit) {

        Weight weight = new Weight(value, fromUnit);

        return weight.convertTo(toUnit);
    }

    // Overloaded conversion
    public static Weight demonstrateWeightConversion(
            Weight weight,
            WeightUnit toUnit) {

        return weight.convertTo(toUnit);
    }

    // Weight Addition
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2) {

        return weight1.add(weight2);
    }

    // Addition with target unit
    public static Weight demonstrateWeightAddition(
            Weight weight1,
            Weight weight2,
            WeightUnit targetUnit) {

        return weight1.add(weight2, targetUnit);
    }

    public static void main(String[] args) {

        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000, WeightUnit.GRAM);

        Weight result = demonstrateWeightAddition(w1, w2);

        System.out.println(result);
    }
}