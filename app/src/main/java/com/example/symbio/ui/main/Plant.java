package com.example.symbio.ui.main;

public class Plant {


    String name;
    String description;
    String favourableLight;
    String soilType;
    String wateringCondition;
    String maxProduction;
    String image;

    public Plant() {

    }

    public Plant(String plantName, String plantDescription, String plantFavourableLight, String plantSoilType, String plantWateringCondition, String plantMaxProduction, String plantImage) {
        name = plantName;
        description = plantDescription;
        favourableLight = plantFavourableLight;
        soilType = plantSoilType;
        wateringCondition = plantWateringCondition;
        maxProduction = plantMaxProduction;
        image = plantImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFavourableLight() {
        return favourableLight;
    }

    public void setFavourableLight(String favourableLight) {
        this.favourableLight = favourableLight;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getWateringCondition() {
        return wateringCondition;
    }

    public void setWateringCondition(String wateringCondition) {
        this.wateringCondition = wateringCondition;
    }

    public String getMaxProduction() {
        return maxProduction;
    }

    public void setMaxProduction(String maxProduction) {
        this.maxProduction = maxProduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


