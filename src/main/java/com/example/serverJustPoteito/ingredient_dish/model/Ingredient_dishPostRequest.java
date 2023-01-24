package com.example.serverJustPoteito.ingredient_dish.model;

import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;

public class Ingredient_dishPostRequest {
    private Ingredient ingredient;
    private Integer ingredientId;
    private Dish dish;
    private Integer dishId;
    private Integer amount;

    public Ingredient_dishPostRequest() {
    }

    public Ingredient_dishPostRequest(Ingredient ingredient, Integer ingredientId, Dish dish, Integer dishId, Integer amount) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient_dishPostRequest{" +
                "ingredient=" + ingredient +
                ", ingredientId=" + ingredientId +
                ", dish=" + dish +
                ", dishId=" + dishId +
                ", amount=" + amount +
                '}';
    }
}
