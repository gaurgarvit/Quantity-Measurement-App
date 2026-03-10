package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12, LengthUnit.INCHES);

        System.out.println(l1.add(l2));

        Quantity<WeightUnit> w1 =
                new Quantity<>(10, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> w2 =
                new Quantity<>(5000, WeightUnit.GRAM);

        System.out.println(w1.add(w2, WeightUnit.GRAM));

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(2, VolumeUnit.LITRE);

        System.out.println(v1.subtract(v2));

        System.out.println(l1.divide(new Quantity<>(2, LengthUnit.FEET)));
    }
}