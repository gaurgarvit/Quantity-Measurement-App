package org.example;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /*
     ============================
     EQUALS METHOD (Java 8 FIXED)
     ============================
     */

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.toBaseUnit(value);
        double otherBase = ((IMeasurable) other.unit).toBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 0.0001;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    /*
     ============================
     UNIT CONVERSION
     ============================
     */

    public double convertTo(U targetUnit) {

        double base = unit.toBaseUnit(value);
        return targetUnit.fromBaseUnit(base);
    }

    /*
     ============================
     ADD
     ============================
     */

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.ADD);

        double converted = targetUnit.fromBaseUnit(baseResult);

        return new Quantity<U>(roundToTwoDecimals(converted), targetUnit);
    }

    /*
     ============================
     SUBTRACT
     ============================
     */

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult =
                performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double converted = targetUnit.fromBaseUnit(baseResult);

        return new Quantity<U>(roundToTwoDecimals(converted), targetUnit);
    }

    /*
     ============================
     DIVIDE
     ============================
     */

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    /*
     ============================
     VALIDATION HELPER (UC13)
     ============================
     */

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit types");

        if (!Double.isFinite(value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");
    }

    /*
     ============================
     CENTRAL ARITHMETIC METHOD
     ============================
     */

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double baseThis = unit.toBaseUnit(value);
        double baseOther = other.unit.toBaseUnit(other.value);

        return operation.compute(baseThis, baseOther);
    }

    /*
     ============================
     ENUM FOR OPERATIONS
     ============================
     */

    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (b == 0.0)
                throw new ArithmeticException("Divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation) {
            this.operation = operation;
        }

        public double compute(double a, double b) {
            return operation.applyAsDouble(a, b);
        }
    }

    /*
     ============================
     ROUNDING
     ============================
     */

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}