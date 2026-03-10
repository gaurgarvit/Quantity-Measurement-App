package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInchesComparison() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void convertFeetToInches() {

        Length result = QuantityMeasurementApp.demonstrateLengthConversion(
                3.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        Length expected = new Length(36.0, LengthUnit.INCHES);

        assertTrue(result.equals(expected));
    }

    @Test
    public void addFeetAndInches() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length result =
                QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

        Length expected = new Length(2.0, LengthUnit.FEET);

        assertTrue(result.equals(expected));
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        Length result =
                QuantityMeasurementApp.demonstrateLengthAddition(
                        length1,
                        length2,
                        LengthUnit.INCHES
                );

        Length expected = new Length(24.0, LengthUnit.INCHES);

        assertTrue(result.equals(expected));
    }
}