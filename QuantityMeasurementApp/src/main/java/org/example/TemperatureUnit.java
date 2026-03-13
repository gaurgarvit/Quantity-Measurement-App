package org.example;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    private final Function<Double, Double> conversionValue;

    SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(boolean isFahrenheit) {

        if (isFahrenheit)
            conversionValue = f -> (f - 32) * 5 / 9;
        else
            conversionValue = c -> c;
    }

    public String getUnitName() {
        return name();
    }

    public double getConversionFactor() {
        return 1.0;
    }

    public double convertToBaseUnit(double value) {
        return conversionValue.apply(value);
    }

    public double convertFromBaseUnit(double baseValue) {

        if (this == FAHRENHEIT)
            return baseValue * 9 / 5 + 32;

        return baseValue;
    }

    public double convertTo(double value, TemperatureUnit targetUnit) {

        double base = convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(base);
    }

    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operation."
            );
        }
    }
}