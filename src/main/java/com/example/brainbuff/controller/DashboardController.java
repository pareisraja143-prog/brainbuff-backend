package com.example.brainbuff.controller;

import com.example.brainbuff.dto.DashboardStatsDto;
import com.example.brainbuff.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<DashboardStatsDto> getStats() {
        DashboardStatsDto stats = dashboardService.getStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
