package com.hackato2025.demo.controller;

import com.hackato2025.demo.service.HutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hut")
public class HutController {
    private final HutService hutService;

    public HutController(HutService hutService) {
        this.hutService = hutService;
    }

    @GetMapping("/full-data")
    public ResponseEntity<List<Map<String, Object>>> getFullMetroData() {
        return ResponseEntity.ok(hutService.getHutOfAllNeighborhoods());
    }
}
