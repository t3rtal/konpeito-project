package com.tertal.konpeito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tertal.konpeito.dto.StatisticsDto;
import com.tertal.konpeito.service.StatisticsService;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics() {
        StatisticsDto dto = this.service.getStatistics();
        return ResponseEntity.ok(dto);
    }

}