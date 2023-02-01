package com.example.serverJustPoteito.dish.model;

import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DishServiceModel {

    private Integer id;
    private String name;
    private Integer prepTime;
    private String subtype;
    private CuisineTypeServiceModel cuisineTypeServiceModel;
    private Integer cuisineTypeId;
    private String recipe;

    public DishServiceModel() {
    }

    public DishServiceModel(Integer id, String name, Integer prepTime, String subtype, CuisineTypeServiceModel cuisineTypeServiceModel, Integer cuisineTypeId, String recipe) {
        this.id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CuisineTypeServiceModel getCuisineTypeServiceModel() {
        return cuisineTypeServiceModel;
    }

    public void setCuisineTypeServiceModel(CuisineTypeServiceModel cuisineTypeServiceModel) {
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
    }

    public Integer getCuisineTypeId() {
        return cuisineTypeId;
    }

    public void setCuisineTypeId(Integer cuisineTypeId) {
        this.cuisineTypeId = cuisineTypeId;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "DishServiceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", cuisineTypeServiceModel=" + cuisineTypeServiceModel +
                ", cuisineTypeId=" + cuisineTypeId +
                ", recipe=" + recipe +
                '}';
    }
}
