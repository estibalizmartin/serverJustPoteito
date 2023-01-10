package com.example.serverJustPoteito.ingredient.service;

import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;

public interface IngredientService {
    Iterable<IngredientServiceModel> getAllIngredients();
    IngredientServiceModel getIngredientById(Integer id);
    IngredientServiceModel createIngredient(IngredientPostRequest ingredientsPostRequest);
    IngredientServiceModel updateIngredient(Integer id, IngredientPostRequest ingredientsPostRequest);
    void deleteIngredientsById(Integer id);
    boolean isAlreadyExists(Integer id);
}
