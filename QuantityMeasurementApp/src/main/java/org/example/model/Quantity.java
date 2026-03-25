package org.example.model;

public class Quantity {
    private double value;
    private Unit unit;

    public Quantity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double toBase() {
        return value * unit.getConversionFactor();
    }

    public double getValue() { return value; }
    public Unit getUnit() { return unit; }
}
