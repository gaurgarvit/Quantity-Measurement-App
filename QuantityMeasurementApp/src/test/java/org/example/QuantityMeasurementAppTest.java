package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    /* =========================
       LENGTH TESTS
       ========================= */

    @Test
    public void lengthFeetEqualsInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void convertLengthFeetToInches() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET);

        double result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void addLengthFeetAndInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void subtractLengthFeetAndInches() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void divideLengthFeetByFeet() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result, EPSILON);
    }

    /* =========================
       WEIGHT TESTS
       ========================= */

    @Test
    public void weightKilogramEqualsGrams() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void convertWeightKilogramsToGrams() {

        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        double result = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result, EPSILON);
    }

    @Test
    public void addWeightKilogramsAndGrams() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(500.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    public void subtractWeightKilogramsAndGrams() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.subtract(q2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void divideWeightKilogramByKilogram() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        double result = q1.divide(q2);

        assertEquals(2.0, result, EPSILON);
    }

    /* =========================
       VOLUME TESTS
       ========================= */

    @Test
    public void volumeLiterEqualsMilliliters() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void convertVolumeLitersToMilliliters() {

        Quantity<VolumeUnit> q =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        double result = q.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result, EPSILON);
    }

    @Test
    public void addVolumeLitersAndMilliliters() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void subtractVolumeLitersAndMilliliters() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.subtract(q2);

        assertEquals(4.5, result.getValue(), EPSILON);
    }

    @Test
    public void divideVolumeLitersByLiters() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        double result = q1.divide(q2);

        assertEquals(0.5, result, EPSILON);
    }

    /* =========================
       EDGE CASES
       ========================= */

    @Test
    public void subtractionResultingZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void subtractionNegativeResult() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void divisionByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    @Test
    public void nullComparison() {

        Quantity<LengthUnit> q =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

}