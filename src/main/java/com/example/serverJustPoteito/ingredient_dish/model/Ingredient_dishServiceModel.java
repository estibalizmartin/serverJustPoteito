package com.example.serverJustPoteito.ingredient_dish.model;

import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ingredient_dishServiceModel {

    private IngredientServiceModel ingredient;
    private Integer ingredientId;
    private DishServiceModel dish;
    private Integer dishId;
    private String amount;

    public Ingredient_dishServiceModel() {
    }

    public Ingredient_dishServiceModel(Integer ingredientId, Integer dishId, String amount) {
        this.ingredientId = ingredientId;
        this.dishId = dishId;
        this.amount = amount;
    }

    public Ingredient_dishServiceModel(IngredientServiceModel ingredient, Integer ingredientId, DishServiceModel dish, Integer dishId, String amount) {
        this.ingredient = ingredient;
        this.ingredientId = ingredientId;
        this.dish = dish;
        this.dishId = dishId;
        this.amount = amount;
    }

    public IngredientServiceModel getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientServiceModel ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public DishServiceModel getDish() {
        return dish;
    }

    public void setDish(DishServiceModel dish) {
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
