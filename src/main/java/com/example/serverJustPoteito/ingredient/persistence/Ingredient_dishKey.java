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
}
