package org.example;

import org.example.dto.QuantityDTO;
import org.example.exception.QuantityMeasurementException;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.repository.QuantityMeasurementCacheRepository;
import org.example.service.IQuantityMeasurementService;
import org.example.service.QuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private IQuantityMeasurementService service;

    @BeforeEach
    void setUp() {
        IQuantityMeasurementRepository repository =
                QuantityMeasurementCacheRepository.getInstance();

        service = new QuantityMeasurementService(repository);
    }

    @Test
    void givenFeetAndInches_whenEqual_shouldReturnTrue() {
        QuantityDTO feet = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO inches = new QuantityDTO(12, "INCHES", "Length");

        assertTrue(service.compare(feet, inches));
    }

    @Test
    void givenDifferentValues_whenNotEqual_shouldReturnFalse() {
        QuantityDTO feet = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO inches = new QuantityDTO(10, "INCHES", "Length");

        assertFalse(service.compare(feet, inches));
    }

    @Test
    void givenDifferentTypes_whenCompare_shouldThrowException() {
        QuantityDTO length = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO weight = new QuantityDTO(1, "KILOGRAM", "Weight");

        assertThrows(QuantityMeasurementException.class,
                () -> service.compare(length, weight));
    }

    @Test
    void givenFeet_whenConvertToInches_shouldReturnCorrectValue() {
        QuantityDTO feet = new QuantityDTO(1, "FEET", "Length");

        QuantityDTO result = service.convert(feet, "INCHES");

        assertEquals(12.0, result.getValue(), 0.001);
    }

    @Test
    void givenGram_whenConvertToKilogram_shouldReturnCorrectValue() {
        QuantityDTO gram = new QuantityDTO(1000, "GRAM", "Weight");

        QuantityDTO result = service.convert(gram, "KILOGRAM");

        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    void givenFeetAndInches_whenAdd_shouldReturnCorrectResult() {
        QuantityDTO feet = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO inches = new QuantityDTO(12, "INCHES", "Length");

        QuantityDTO result = service.add(feet, inches);

        assertEquals(2.0, result.getValue(), 0.001);
    }

    @Test
    void givenDifferentTypes_whenAdd_shouldThrowException() {
        QuantityDTO length = new QuantityDTO(1, "FEET", "Length");
        QuantityDTO weight = new QuantityDTO(1, "KILOGRAM", "Weight");

        assertThrows(QuantityMeasurementException.class,
                () -> service.add(length, weight));
    }

    @Test
    void givenZeroValues_whenAdd_shouldReturnZero() {
        QuantityDTO q1 = new QuantityDTO(0, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(0, "INCHES", "Length");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(0.0, result.getValue(), 0.001);
    }

    @Test
    void givenNegativeValues_whenAdd_shouldWorkCorrectly() {
        QuantityDTO q1 = new QuantityDTO(-1, "FEET", "Length");
        QuantityDTO q2 = new QuantityDTO(-12, "INCHES", "Length");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(-2.0, result.getValue(), 0.001);
    }
}