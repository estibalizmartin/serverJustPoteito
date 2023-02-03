package com.example.serverJustPoteito.dish.model;

import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DishServiceModel {

    private Integer Id;
    private String name;
    private Integer prepTime;
    private String subtype;
    private String recipe;
    private String image;
    private CuisineTypeServiceModel cuisineTypeServiceModel;
    private Integer cuisineTypeId;
    private CookServiceModel cookServiceModel;
    private Integer cookId;
    private List<Ingredient_dishServiceModel> ingredientDishServiceModels;

    public DishServiceModel() {
    }

    public DishServiceModel(Integer id,
                            String name,
                            Integer prepTime,
                            String subtype,
                            String recipe,
                            String image,
                            CuisineTypeServiceModel cuisineTypeServiceModel,
                            Integer cuisineTypeId,
                            CookServiceModel cookServiceModel,
                            Integer cookId,
                            List<Ingredient_dishServiceModel> ingredientDishServiceModels) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.recipe = recipe;
        this.image = image;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
        this.cookServiceModel = cookServiceModel;
        this.cookId = cookId;
        this.ingredientDishServiceModels = ingredientDishServiceModels;
    }

    /*public DishServiceModel(Integer id,
                            String name,
                            Integer prepTime,
                            String subtype,
                            CuisineTypeServiceModel cuisineTypeServiceModel,
                            Integer cuisineTypeId
    ) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
    }

    public DishServiceModel(Integer id, String name, Integer prepTime, String subtype, CuisineTypeServiceModel cuisineTypeServiceModel, Integer cuisineTypeId, String recipe) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
        this.recipe = recipe;
    }
    public DishServiceModel(Integer id, String name, Integer prepTime, String subtype, CuisineTypeServiceModel cuisineTypeServiceModel, Integer cuisineTypeId, String recipe, String image) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeServiceModel = cuisineTypeServiceModel;
        this.cuisineTypeId = cuisineTypeId;
        this.recipe = recipe;
        this.image = image;
    }*/

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<Ingredient_dishServiceModel> getIngredientDishServiceModels() {
        return ingredientDishServiceModels;
    }

    public void setIngredientDishServiceModels(List<Ingredient_dishServiceModel> ingredientDishServiceModels) {
        this.ingredientDishServiceModels = ingredientDishServiceModels;
    }

    @Override
    public String toString() {
        return "DishServiceModel{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", recipe='" + recipe + '\'' +
                ", image='" + image + '\'' +
                ", cuisineTypeServiceModel=" + cuisineTypeServiceModel +
                ", cuisineTypeId=" + cuisineTypeId +
                ", cookServiceModel=" + cookServiceModel +
                ", ingredientDishServiceModels=" + ingredientDishServiceModels +
                '}';
    }
}
