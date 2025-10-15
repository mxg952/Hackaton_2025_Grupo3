package com.hackato2025.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Neighborhood {

    // Getters and setters
    private String neighborhoodCode;
    private String neighborhoodName;
    private String districtCode;
    private String districtName;
    private int population2024;
    private int populationDensityKm2;
    private MonthlyTourism monthlyTourism;
    private int totalHUTs;
}

