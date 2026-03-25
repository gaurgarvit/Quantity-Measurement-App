package org.example.model;

public enum LengthUnit implements Unit {

    FEET {
        public double toBase(double v) { return v; }
        public double fromBase(double v) { return v; }
    },

    INCHES {
        public double toBase(double v) { return v / 12; }
        public double fromBase(double v) { return v * 12; }
    };

    public String getUnitName() {
        return name();
    }

    public String getMeasurementType() {
        return "Length";
    }
}