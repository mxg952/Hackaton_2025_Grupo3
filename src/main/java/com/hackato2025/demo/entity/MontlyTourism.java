package com.hackato2025.demo.entity;

import java.util.Map;

public class MontlyTourism {

    private Map<String, Integer> absoluteTouristsByDistrict;
    private Map<String, String> districtTourismPercentage;

    public Map<String, Integer> getAbsoluteTouristsByDistrict() {
        return absoluteTouristsByDistrict;
    }

    public void setAbsoluteTouristsByDistrict(Map<String, Integer> absoluteTouristsByDistrict) {
        this.absoluteTouristsByDistrict = absoluteTouristsByDistrict;
    }

    public Map<String, String> getDistrictTourismPercentage() {
        return districtTourismPercentage;
    }

    public void setDistrictTourismPercentage(Map<String, String> districtTourismPercentage) {
        this.districtTourismPercentage = districtTourismPercentage;
    }
}
