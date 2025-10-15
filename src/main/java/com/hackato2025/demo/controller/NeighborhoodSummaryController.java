package com.hackato2025.demo.controller;

import com.hackato2025.demo.service.HutService;
import com.hackato2025.demo.service.NeighborhoodSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
public class NeighborhoodSummaryController {
    private final NeighborhoodSummaryService neighborhoodSummaryService;

    public NeighborhoodSummaryController(NeighborhoodSummaryService neighborhoodSummaryService) {
        this.neighborhoodSummaryService = neighborhoodSummaryService;
    }

    @GetMapping("/full-data")
    public ResponseEntity<List<Map<String, Object>>> getFullSummaryData() {
        return ResponseEntity.ok(neighborhoodSummaryService.getSummaryData());
    }
}
