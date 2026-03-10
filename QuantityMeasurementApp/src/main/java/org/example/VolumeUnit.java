package org.example;

public enum VolumeUnit implements IMeasurable {

    MILLILITRE(1.0),
    LITRE(1000.0);

    private final double toBaseFactor;

    VolumeUnit(double toBaseFactor) {
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