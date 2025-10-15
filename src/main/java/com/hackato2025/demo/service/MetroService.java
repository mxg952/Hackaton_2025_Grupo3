package com.hackato2025.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class MetroService {

    private List<Map<String, Object>> metroData;

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/flujoturismo.json");
        if (inputStream == null) {
            throw new RuntimeException("File flujoturismo.json not found in resources/data/");
        }
        metroData = mapper.readValue(inputStream, new TypeReference<>() {});
    }

    public List<Map<String, Object>> getFullMetroData() {
        return metroData;
    }
}