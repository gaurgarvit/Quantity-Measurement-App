package org.example.model;

public interface Unit {
    double toBase(double value);
    double fromBase(double baseValue);
    String getUnitName();
    String getMeasurementType();
}
