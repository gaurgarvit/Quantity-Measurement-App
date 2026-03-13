package org.example;

public class Quantity<U extends IMeasurable> {

    private double value;
    private U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    public double convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(base);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result =
                performArithmetic(other, targetUnit, ArithmeticOperation.ADD);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result =
                performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);

        return new Quantity<>(result, targetUnit);
    }

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Incompatible unit types");

        if (!Double.isFinite(value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");
    }

    private double performArithmetic(
            Quantity<U> other,
            U targetUnit,
            ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double result = operation.compute(base1, base2);

        if (operation == ArithmeticOperation.DIVIDE)
            return result;

        return targetUnit.convertFromBaseUnit(result);
    }

    private enum ArithmeticOperation {

        ADD {
            @Override
            public double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            @Override
            public double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            @Override
            public double compute(double a, double b) {

                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");

                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}