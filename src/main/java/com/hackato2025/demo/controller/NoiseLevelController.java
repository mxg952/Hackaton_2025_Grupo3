package com.hackato2025.demo.controller;

import com.hackato2025.demo.service.HutService;
import com.hackato2025.demo.service.NoiseLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/noise-level")
public class NoiseLevelController {
    private final NoiseLevelService noiseLevelService;

    public NoiseLevelController(NoiseLevelService noiseLevelService) {
        this.noiseLevelService = noiseLevelService;
    }

    @GetMapping("/full-data")
    public ResponseEntity<List<Map<String, Object>>> getFullMetroData() {
        return ResponseEntity.ok(noiseLevelService.getHutOfAllNeighborhoods());
    }
}
