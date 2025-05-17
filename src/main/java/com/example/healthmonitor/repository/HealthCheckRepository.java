package com.example.healthmonitor.repository;

import com.example.healthmonitor.model.HealthCheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthCheckRepository extends JpaRepository<HealthCheckRecord, Long> {
    List<HealthCheckRecord> findAllByOrderByCheckTimeDesc();
}