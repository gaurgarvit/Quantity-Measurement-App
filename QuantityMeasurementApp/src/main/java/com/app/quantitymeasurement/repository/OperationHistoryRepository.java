package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.entity.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {

    List<OperationHistory> findByOperationIgnoreCase(String operation);

    List<OperationHistory> findByTypeIgnoreCase(String type);

    List<OperationHistory> findByErrorTrue();

    long countByOperationIgnoreCase(String operation);
}