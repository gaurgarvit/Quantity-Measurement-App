package org.example.model;

public enum WeightUnit implements Unit {
    KILOGRAM(1.0),
    GRAM(0.001),
    TONNE(1000);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }

    public String getUnitName() { return name(); }

    @Override
    public String getMeasurementType() {
        return "Weight";
    }
}
