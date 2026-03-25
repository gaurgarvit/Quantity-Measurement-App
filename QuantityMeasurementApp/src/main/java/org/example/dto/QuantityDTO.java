package org.example.dto;

public class QuantityDTO {
    private double value;
    private String unitName;
    private String measurementType;

    public QuantityDTO(double value, String unitName, String measurementType) {
        this.value = value;
        this.unitName = unitName;
        this.measurementType = measurementType;
    }

    public double getValue() { return value; }
    public String getUnitName() { return unitName; }
    public String getMeasurementType() { return measurementType; }
}