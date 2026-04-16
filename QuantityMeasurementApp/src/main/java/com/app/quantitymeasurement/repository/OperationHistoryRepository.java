package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.OperationHistory;
import com.app.quantitymeasurement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {

    List<OperationHistory> findByOperationIgnoreCase(String operation);

    List<OperationHistory> findByMeasurementTypeIgnoreCase(String measurementType); // ✅ FIXED

    List<OperationHistory> findByErrorTrue();

    List<OperationHistory> findByUser(User user);

    long countByOperationIgnoreCase(String operation);
}