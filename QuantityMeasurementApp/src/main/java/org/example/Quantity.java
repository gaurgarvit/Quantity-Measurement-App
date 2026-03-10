package org.example;

public class Quantity<U extends IMeasurable> {

    private double value;
    private U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public double convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);

        return targetUnit.convertFromBaseUnit(base);
    }

    public Quantity<U> add(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    public double divide(Quantity<U> other) {

        if (other.value == 0)
            throw new ArithmeticException("Division by zero");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return base1 / base2;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}