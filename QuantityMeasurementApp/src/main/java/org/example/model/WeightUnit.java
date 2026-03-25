package org.example.model;

public enum WeightUnit implements Unit {

    KILOGRAM {
        public double toBase(double v) { return v; }
        public double fromBase(double v) { return v; }
    },

    GRAM {
        public double toBase(double v) { return v / 1000; }
        public double fromBase(double v) { return v * 1000; }
    };

    public String getUnitName() {
        return name();
    }

    public String getMeasurementType() {
        return "Weight";
    }
}