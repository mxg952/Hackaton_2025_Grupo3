package com.hackato2025.demo.controller;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackato2025.demo.model.Neighborhood;
import com.hackato2025.demo.service.NeighborhoodService;

@RestController
@RequestMapping("/api/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping
    public List<Neighborhood> getAll() {
        return neighborhoodService.getAllNeighborhoods();
    }

    @GetMapping("/visitors/total")
    public Map<String, Integer> getTotalVisitorsPerMonth() {
        return neighborhoodService.getTotalVisitorsPerMonth();
    }
}
