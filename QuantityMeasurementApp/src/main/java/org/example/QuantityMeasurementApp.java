package org.example;


public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        boolean result = f1.equals(f2);

        System.out.println("Feet equality result: " + result);
    }
}