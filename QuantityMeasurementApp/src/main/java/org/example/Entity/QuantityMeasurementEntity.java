package org.example.Entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String input1;
    private String input2;
    private String result;
    private boolean isError;
    private String errorMessage;

    // Success constructor
    public QuantityMeasurementEntity(String operation,
                                     String input1,
                                     String input2,
                                     String result) {
        this.operation = operation;
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
        this.isError = false;
    }

    // Error constructor
    public QuantityMeasurementEntity(String operation, String errorMessage) {
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.isError = true;
    }

    @Override
    public String toString() {
        if (isError) {
            return "ERROR in " + operation + ": " + errorMessage;
        }
        return operation + ": " + input1 + ", " + input2 + " → " + result;
    }
}
