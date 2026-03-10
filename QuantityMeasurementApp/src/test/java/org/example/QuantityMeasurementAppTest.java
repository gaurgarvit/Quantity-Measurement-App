package org.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void kilogramEquals1000Grams() {

        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    public void poundEquals453Point592Grams() {

        Weight w1 = new Weight(1, WeightUnit.POUND);
        Weight w2 = new Weight(453.592, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    public void tonneEquals1000000Grams() {

        Weight w1 = new Weight(1, WeightUnit.TONNE);
        Weight w2 = new Weight(1000000, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    public void kilogramNotEqualToPound() {

        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1, WeightUnit.POUND);

        assertFalse(w1.equals(w2));
    }

    @Test
    public void additionOfWeightsEqualsExpected() {

        Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000, WeightUnit.GRAM);

        Weight result = w1.add(w2);

        Weight expected = new Weight(2, WeightUnit.KILOGRAM);

        assertTrue(result.equals(expected));
    }
}