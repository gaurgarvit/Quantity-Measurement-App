package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void addFeetAndInches() {

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length sumLength =
                QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

        Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength)
        );
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length sumLength =
                QuantityMeasurementApp.demonstrateLengthAddition(
                        length1,
                        length2,
                        Length.LengthUnit.INCHES
                );

        Length expectedLength = new Length(24.0, Length.LengthUnit.INCHES);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength)
        );
    }

    @Test
    public void addFeetAndInchesWithTargetUnitYards() {

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result =
                QuantityMeasurementApp.demonstrateLengthAddition(
                        length1,
                        length2,
                        Length.LengthUnit.YARDS
                );

        Length expected = new Length(0.67, Length.LengthUnit.YARDS);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(result, expected)
        );
    }
}