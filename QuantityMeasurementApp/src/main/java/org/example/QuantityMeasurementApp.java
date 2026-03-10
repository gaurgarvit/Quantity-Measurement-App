package org.example;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.equals(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {

        return new Quantity<>(quantity.convertTo(targetUnit), targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.add(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.add(q2, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.subtract(q2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        return q1.subtract(q2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {

        return q1.divide(q2);
    }

}