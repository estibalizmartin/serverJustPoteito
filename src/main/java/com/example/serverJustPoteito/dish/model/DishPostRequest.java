package com.example.serverJustPoteito.dish.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DishPostRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    private Integer prepTime;

//    private Allergens allergen;

    private String subtype;

    private Integer cuisineTypeId;

    public DishPostRequest() {
    }

    public DishPostRequest(String name, Integer prepTime, String subtype, Integer cuisineTypeId) {
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeId = cuisineTypeId;
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

    public Integer getCuisineTypeId() {
        return cuisineTypeId;
    }

    public void setCuisineTypeId(Integer cuisineTypeId) {
        this.cuisineTypeId = cuisineTypeId;
    }

    @Override
    public String toString() {
        return "DishPostRequest{" +
                "name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", cuisineTypeId=" + cuisineTypeId +
                '}';
    }
}
