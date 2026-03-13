package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    /* ==============================
       TEMPERATURE EQUALITY TESTS
     ============================== */

    @Test
    public void testTemperatureEquality_CelsiusToCelsius() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        assertTrue(t1.equals(t2));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(t1.equals(t2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    public void testTemperatureEquality_NegativeForty() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
    }

    @Test
    public void testTemperatureEquality_DifferentValues() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t1.equals(t2));
    }

    /* ==============================
       TEMPERATURE CONVERSION TESTS
     ============================== */

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> fahrenheit =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                fahrenheit.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_RoundTrip() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahrenheit =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> back =
                fahrenheit.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(50.0, back.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_NegativeValues() {

        Quantity<TemperatureUnit> celsius =
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                celsius.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-4.0, result.getValue(), EPSILON);
    }

    /* ==============================
       UNSUPPORTED OPERATION TESTS
     ============================== */

    @Test
    public void testTemperatureUnsupportedAddition() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2)
        );
    }

    @Test
    public void testTemperatureUnsupportedSubtraction() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.subtract(t2)
        );
    }

    @Test
    public void testTemperatureUnsupportedDivision() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2)
        );
    }

    /* ==============================
       CROSS CATEGORY SAFETY
     ============================== */

    @Test
    public void testTemperatureVsLengthComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<LengthUnit> length =
                new Quantity<>(50.0, LengthUnit.FEET);

        assertFalse(temp.equals(length));
    }

    @Test
    public void testTemperatureVsWeightComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<WeightUnit> weight =
                new Quantity<>(50.0, WeightUnit.KILOGRAM);

        assertFalse(temp.equals(weight));
    }

    @Test
    public void testTemperatureVsVolumeComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<VolumeUnit> volume =
                new Quantity<>(50.0, VolumeUnit.LITRE);

        assertFalse(temp.equals(volume));
    }

}