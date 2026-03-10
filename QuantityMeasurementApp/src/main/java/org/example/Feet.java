package org.example;

public class Feet {

    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        // Same reference
        if (this == obj)
            return true;

        // Null check
        if (obj == null)
            return false;

        // Type check
        if (getClass() != obj.getClass())
            return false;

        // Cast object
        Feet other = (Feet) obj;

        // Compare values
        return Double.compare(this.value, other.value) == 0;
    }
}