package com.example.serverJustPoteito.dish.model;

import com.example.serverJustPoteito.cook.model.Cook;
import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DishPostRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    @NotNull
    private Integer prepTime;
    @NotNull
    @NotBlank
    @NotEmpty
    private String subtype;
    private String image;
    @NotNull
    @NotBlank
    @NotEmpty
    private String recipe;
    @NotNull
    private Integer cuisineTypeId;
    @NotNull
    private Integer cookId;

    public DishPostRequest() {
    }

    public DishPostRequest(String name,
                           Integer prepTime,
                           String subtype,
                           String image,
                           String recipe,
                           Integer cuisineTypeId,
                           Integer cookId) {
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.image = image;
        this.recipe = recipe;
        this.cuisineTypeId = cuisineTypeId;
        this.cookId = cookId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Integer getCuisineTypeId() {
        return cuisineTypeId;
    }

    public void setCuisineTypeId(Integer cuisineTypeId) {
        this.cuisineTypeId = cuisineTypeId;
    }

    public Integer getCookId() {
        return cookId;
    }

    public void setCookId(Integer cookId) {
        this.cookId = cookId;
    }

    @Override
    public String toString() {
        return "DishPostRequest{" +
                "name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", image='" + image + '\'' +
                ", recipe='" + recipe + '\'' +
                ", cuisineTypeId=" + cuisineTypeId +
                ", cookId=" + cookId +
                '}';
    }
}
