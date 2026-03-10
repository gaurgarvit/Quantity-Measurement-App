package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    /*
    =============================
    ADDITION TESTS
    =============================
    */

    @Test
    public void addLengthFeetAndInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void addWeightKilogramsAndGramsWithTargetUnit() {

        Quantity<WeightUnit> kg =
                new Quantity<>(10, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(5000, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.GRAM);

        assertEquals(15000.0, result.getValue());
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void addVolumesLitersAndMilliliters() {

        Quantity<VolumeUnit> liters =
                new Quantity<>(2, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(500, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = liters.add(ml);

        assertEquals(2.5, result.getValue());
    }

    /*
    =============================
    SUBTRACTION TESTS
    =============================
    */

    @Test
    public void subtractLengthFeetAndInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(6, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.subtract(inches);

        assertEquals(9.5, result.getValue());
    }

    @Test
    public void subtractVolumeWithTargetUnit() {

        Quantity<VolumeUnit> l1 =
                new Quantity<>(5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> l2 =
                new Quantity<>(2, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                l1.subtract(l2, VolumeUnit.MILLILITRE);

        assertEquals(3000.0, result.getValue());
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    /*
    =============================
    DIVISION TESTS
    =============================
    */

    @Test
    public void divideLengthsSameUnit() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result);
    }

    @Test
    public void divideLengthsDifferentUnits() {

        Quantity<LengthUnit> inches =
                new Quantity<>(24, LengthUnit.INCHES);

        Quantity<LengthUnit> feet =
                new Quantity<>(2, LengthUnit.FEET);

        double result = inches.divide(feet);

        assertEquals(1.0, result);
    }

    /*
    =============================
    ERROR TESTS
    =============================
    */

    @Test
    public void addNullShouldThrowException() {

        Quantity<LengthUnit> q =
                new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.add(null));
    }

    @Test
    public void subtractDifferentCategoriesShouldThrow() {

        Quantity<LengthUnit> length =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight));
    }

    @Test
    public void divideByZeroShouldThrowException() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    /*
    =============================
    EQUALITY TEST
    =============================
    */

    @Test
    public void equalityFeetAndInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    /*
    =============================
    CONVERSION TEST
    =============================
    */

    @Test
    public void convertFeetToInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        double result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result);
    }

    /*
    =============================
    ROUNDING TEST
    =============================
    */

    @Test
    public void roundingTest() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.333, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(1.333, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.67, result.getValue());
    }
}