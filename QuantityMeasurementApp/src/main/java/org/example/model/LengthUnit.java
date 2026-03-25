package org.example.model;

public enum LengthUnit implements Unit {
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public String getUnitName() { return name(); }

    @Override
    public String getMeasurementType() {
        return "Length";
    }
}
