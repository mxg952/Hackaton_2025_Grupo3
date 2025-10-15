package com.hackato2025.demo.controller;


import com.hackato2025.demo.model.Neighborhood;
import com.hackato2025.demo.service.NeighborhoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
