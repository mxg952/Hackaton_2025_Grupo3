package com.hackato2025.demo.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackato2025.demo.model.Neighborhood;
import lombok.Getter;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class NeighborhoodService {

    private List<Map<String, Object>> neighborhoodsFullData;

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = getClass().getResourceAsStream("/data/neighborhoods.json");
        if (inputStream == null) {
            throw new RuntimeException("File neighborhoods.json not found in resources/");
        }

        neighborhoodsFullData = mapper.readValue(inputStream, new TypeReference<>() {});
    }

    public List<Map<String, Object>> getFullNeighborhoodsData() {
        return neighborhoodsFullData;
    }

}
