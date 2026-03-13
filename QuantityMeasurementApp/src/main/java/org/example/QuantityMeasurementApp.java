package org.example;

public class QuantityMeasurementApp {
        // will do
    public static void main(String[] args) {

        Quantity<LengthUnit> l1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12, LengthUnit.INCHES);

        System.out.println("Length equal: " + l1.equals(l2));

        Quantity<WeightUnit> w1 =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(1000, WeightUnit.GRAMS);

        System.out.println("Weight equal: " + w1.equals(w2));

        Quantity<VolumeUnit> v1 =
                new Quantity<>(1, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(1000, VolumeUnit.MILLILITRE);

        System.out.println("Volume equal: " + v1.equals(v2));
    }
}