package com.example.serverJustPoteito.ingredient.repository;

import com.example.serverJustPoteito.ingredient.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}