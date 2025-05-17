package com.example.healthmonitor.controller;

import com.example.healthmonitor.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @Autowired
    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/")
    public String showHealthChecks(Model model) {
        model.addAttribute("healthChecks", healthCheckService.getAllHealthChecks());
        return "healthChecks";
    }
}