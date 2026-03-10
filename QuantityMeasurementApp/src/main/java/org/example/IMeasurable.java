package org.example;

public interface IMeasurable {

    double toBaseUnit(double value);

    double fromBaseUnit(double baseValue);
}