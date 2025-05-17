package com.example.healthmonitor.service;

import com.example.healthmonitor.model.HealthCheckRecord;
import com.example.healthmonitor.repository.HealthCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HealthCheckService {

    private final HealthCheckRepository repository;
    private final RestTemplate restTemplate;
    private final String healthCheckUrl;

    @Autowired
    public HealthCheckService(
            HealthCheckRepository repository,
            RestTemplateBuilder restTemplateBuilder,
            @Value("${health.check.url}") String healthCheckUrl) {
        this.repository = repository;
        this.restTemplate = restTemplateBuilder.build();
        this.healthCheckUrl = healthCheckUrl;
    }

    @Scheduled(fixedRate = 840000) // 14 minutes = 840000 milliseconds
    public void performHealthCheck() {
        HealthCheckRecord record = new HealthCheckRecord();
        record.setCheckTime(LocalDateTime.now());

        try {
            String response = restTemplate.getForObject(healthCheckUrl, String.class);
            record.setStatus("UP");
            record.setDetails(response);
        } catch (Exception e) {
            record.setStatus("DOWN");
            record.setDetails(e.getMessage());
        }

        repository.save(record);
    }

    public List<HealthCheckRecord> getAllHealthChecks() {
        return repository.findAllByOrderByCheckTimeDesc();
    }
}