package com.example.serverJustPoteito.dish.model;

import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DishServiceModel {

    private Integer id;
    private String name;
    private Integer prepTime;
    private String subtype;
    private String recipe;
    private CuisineTypeServiceModel cuisineTypeServiceModel;
    private Integer cuisineTypeId;
    private CookServiceModel cookServiceModel;
    private Integer cookId;
    private Ingredient_dishServiceModel ingredientDishServiceModel;

    public DishServiceModel() {
    }

    public DishServiceModel(Integer id,
                            String name,
                            Integer prepTime,
                            String subtype,
                            String recipe,
                            Integer cuisineTypeId,
                            Integer cookId) {
        this.id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.recipe = recipe;
        this.cuisineTypeId = cuisineTypeId;
        this.cookId = cookId;
    }

    public DishServiceModel(Integer id,
                            String name,
                            Integer prepTime,
                            String subtype,
                            String recipe,
                            CuisineTypeServiceModel cuisineTypeServiceModel,
                            Integer cuisineTypeId,
                            CookServiceModel cookServiceModel,
                            Integer cookId,
                            Ingredient_dishServiceModel ingredientDishServiceModel) {
        this.id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.recipe = recipe;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
        this.cookServiceModel = cookServiceModel;
        this.cookId = cookId;
        this.ingredientDishServiceModel = ingredientDishServiceModel;
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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
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

    public CookServiceModel getCookServiceModel() {
        return cookServiceModel;
    }

    public void setCookServiceModel(CookServiceModel cookServiceModel) {
        this.cookServiceModel = cookServiceModel;
    }

    public Integer getCookId() {
        return cookId;
    }

    public void setCookId(Integer cookId) {
        this.cookId = cookId;
    }

    public Ingredient_dishServiceModel getIngredientDishServiceModel() {
        return ingredientDishServiceModel;
    }

    public void setIngredientDishServiceModel(Ingredient_dishServiceModel ingredientDishServiceModel) {
        this.ingredientDishServiceModel = ingredientDishServiceModel;
    }

    @Override
    public String toString() {
        return "DishServiceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", recipe='" + recipe + '\'' +
                ", cuisineTypeServiceModel=" + cuisineTypeServiceModel +
                ", cuisineTypeId=" + cuisineTypeId +
                ", cookServiceModel=" + cookServiceModel +
                ", cookId=" + cookId +
                ", ingredientDishServiceModel=" + ingredientDishServiceModel +
                '}';
    }
}
