package com.tertal.konpeito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tertal.konpeito.dto.StatisticsDto;
import com.tertal.konpeito.service.StatisticsService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics() {
        StatisticsDto dto = this.service.getStatistics();
        return ResponseEntity.ok(dto);
    }

}