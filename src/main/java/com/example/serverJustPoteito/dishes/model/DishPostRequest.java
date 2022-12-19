package com.example.serverJustPoteito.dishes.model;

import com.example.serverJustPoteito.cuisineType.CuisineType;
import jakarta.validation.constraints.NotNull;

public class DishPostRequest {

    @NotNull
    private String name;
    private Integer prepTime;
    //    private String allergen;
    private String subtype;
    private CuisineType cuisineType;

    public DishPostRequest() {
    }

    public DishPostRequest(String name, Integer prepTime, String subtype, CuisineType cuisineType) {
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineType = cuisineType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }
}
