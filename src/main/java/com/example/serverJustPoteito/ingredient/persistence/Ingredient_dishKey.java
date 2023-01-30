package com.example.serverJustPoteito.ingredient.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;
@Embeddable
public class Ingredient_dishKey implements Serializable {

    @Column(name = "ingredient_id")
    Integer ingredientId;

    @Column(name = "dish_id")
    Integer dishId;

    public Ingredient_dishKey() {

    }
    public Ingredient_dishKey(Integer ingredientId, Integer dishId) {
        this.ingredientId = ingredientId;
        this.dishId = dishId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }
}
