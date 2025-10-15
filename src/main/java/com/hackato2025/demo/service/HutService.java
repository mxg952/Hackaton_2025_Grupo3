package com.hackato2025.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HutService {
    private List<Map<String, Object>> hutData = new ArrayList<>();

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/hut.json");
        if (inputStream == null) {
            throw new RuntimeException("File hut.json not found in resources/data/");
        }
        hutData = mapper.readValue(inputStream, new TypeReference<>() {});
    }

    public List<Map<String, Object>> getHutOfAllNeighborhoods() {
        return hutData;
    }
}
