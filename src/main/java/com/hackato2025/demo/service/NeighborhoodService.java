package com.hackato2025.demo.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackato2025.demo.model.Neighborhood;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

@Service
public class NeighborhoodService {

    private List<Neighborhood> neighborhoods = new ArrayList<>();

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data/neighborhoods.json");
        neighborhoods = mapper.readValue(inputStream, new TypeReference<>() {});
    }

    public List<Neighborhood> getAllNeighborhoods() {
        return neighborhoods;
    }

    public Map<String, Integer> getTotalVisitorsPerMonth() {
        Map<String, Integer> totals = new LinkedHashMap<>();

        for (Neighborhood n : neighborhoods) {
            if (n.getMonthlyTourism() == null) continue;
            Map<String, Integer> tourism = n.getMonthlyTourism().getAbsoluteTouristsByDistrict();
            if (tourism == null) continue;

            for (Map.Entry<String, Integer> entry : tourism.entrySet()) {
                totals.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
        return totals;
    }
}
