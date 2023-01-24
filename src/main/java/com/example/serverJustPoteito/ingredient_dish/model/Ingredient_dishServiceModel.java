package com.example.serverJustPoteito.ingredient_dish.model;

import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ingredient_dishServiceModel {

    private Ingredient ingredient;
    private Integer ingredientId;
    private Dish dish;
    private Integer dishId;
    private String amount;

    public Ingredient_dishServiceModel() {
    }

    public Ingredient_dishServiceModel(Integer ingredientId, Integer dishId, String amount) {
        this.ingredientId = ingredientId;
        this.dishId = dishId;
        this.amount = amount;
    }

    public Ingredient_dishServiceModel(Ingredient ingredient, Integer ingredientId, Dish dish, Integer dishId, String amount) {
        this.ingredient = ingredient;
        this.ingredientId = ingredientId;
        this.dish = dish;
        this.dishId = dishId;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient_dishServiceModel{" +
                "ingredient=" + ingredient +
                ", ingredientId=" + ingredientId +
                ", dish=" + dish +
                ", dishId=" + dishId +
                ", amount=" + amount +
                '}';
    }
}
