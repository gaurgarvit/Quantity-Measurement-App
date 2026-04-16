package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OperationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private String measurementType;

    private double inputValue1;
    private String inputUnit1;

    private Double inputValue2;
    private String inputUnit2;

    private Double resultValue;
    private String resultUnit;
    private String resultString;
    
    private boolean error;

    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public OperationHistory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getMeasurementType() { return measurementType; }
    public void setMeasurementType(String measurementType) { this.measurementType = measurementType; }

    public double getInputValue1() { return inputValue1; }
    public void setInputValue1(double inputValue1) { this.inputValue1 = inputValue1; }

    public String getInputUnit1() { return inputUnit1; }
    public void setInputUnit1(String inputUnit1) { this.inputUnit1 = inputUnit1; }

    public Double getInputValue2() { return inputValue2; }
    public void setInputValue2(Double inputValue2) { this.inputValue2 = inputValue2; }

    public String getInputUnit2() { return inputUnit2; }
    public void setInputUnit2(String inputUnit2) { this.inputUnit2 = inputUnit2; }

    public Double getResultValue() { return resultValue; }
    public void setResultValue(Double resultValue) { this.resultValue = resultValue; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }

    public String getResultString() { return resultString; }
    public void setResultString(String resultString) { this.resultString = resultString; }

    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
