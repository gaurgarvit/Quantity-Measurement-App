package org.example;

public enum LengthUnit implements IMeasurable {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double toBaseFactor;

    LengthUnit(double toBaseFactor) {
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