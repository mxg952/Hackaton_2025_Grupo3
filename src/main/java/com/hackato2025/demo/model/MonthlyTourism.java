package com.hackato2025.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class MonthlyTourism {
    private Map<String, Integer> absoluteTouristsByDistrict;
    private Map<String, String> districtTourismPercentage;

}
