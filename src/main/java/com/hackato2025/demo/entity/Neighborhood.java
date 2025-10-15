package com.hackato2025.demo.entity;

public class Neighborhood {

    private String neighborhoodCode;
    private String neighborhoodName;
    private String districtCode;
    private String districtName;
    private int population2024;
    private int populationDensityKm2;
    private MonthlyTourism monthlyTourism;

    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getPopulation2024() {
        return population2024;
    }

    public void setPopulation2024(int population2024) {
        this.population2024 = population2024;
    }

    public int getPopulationDensityKm2() {
        return populationDensityKm2;
    }

    public void setPopulationDensityKm2(int populationDensityKm2) {
        this.populationDensityKm2 = populationDensityKm2;
    }

    public MonthlyTourism getMonthlyTourism() {
        return monthlyTourism;
    }

    public void setMonthlyTourism(MonthlyTourism monthlyTourism) {
        this.monthlyTourism = monthlyTourism;
    }
}
