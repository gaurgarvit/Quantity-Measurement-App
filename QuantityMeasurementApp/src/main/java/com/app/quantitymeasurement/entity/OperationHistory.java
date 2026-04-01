package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    
    private boolean error;

    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}