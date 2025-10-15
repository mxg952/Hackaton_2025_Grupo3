package com.hackato2025.demo.controller;

import com.hackato2025.demo.service.MetroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metro")
public class MetroController {

    private final MetroService metroService;

    public MetroController(MetroService metroService) {
        this.metroService = metroService;
    }

    @GetMapping("/full-data")
    public ResponseEntity<List<Map<String, Object>>> getFullMetroData() {
        return ResponseEntity.ok(metroService.getFullMetroData());
    }
}
