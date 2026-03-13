package org.example;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAMS(0.001),
    POUND(0.453592),
    TONNE(1000);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getUnitName() {
        return name();
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}