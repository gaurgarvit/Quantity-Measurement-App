package org.example.model;

public enum TemperatureUnit {
    CELSIUS, FAHRENHEIT;

    public double convert(double value, TemperatureUnit target) {
        if (this == CELSIUS && target == FAHRENHEIT)
            return (value * 9 / 5) + 32;
        if (this == FAHRENHEIT && target == CELSIUS)
            return (value - 32) * 5 / 9;
        return value;
    }
}