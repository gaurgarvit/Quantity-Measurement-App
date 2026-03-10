package org.example;

public enum WeightUnit implements IMeasurable {

    GRAM(1.0),
    KILOGRAM(1000.0),
    TONNE(1000000.0),
    POUND(453.592);

    private final double toBaseFactor;

    WeightUnit(double toBaseFactor) {
        this.toBaseFactor = toBaseFactor;
    }

    @Override
    public double toBaseUnit(double value) {
        return value * toBaseFactor;
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / toBaseFactor;
    }
}