package com.example.serverJustPoteito.ingredients.service;

import com.example.serverJustPoteito.ingredients.model.IngredientsServiceModel;
import com.example.serverJustPoteito.ingredients.model.IngredientsPostRequest;

public interface IngredientsService {
    Iterable<IngredientsServiceModel> getAllIngredients();
    IngredientsServiceModel getIngredientById(Integer id);
    IngredientsServiceModel createIngredient(IngredientsPostRequest ingredientsPostRequest);
    IngredientsServiceModel updateIngredient(Integer id, IngredientsPostRequest ingredientsPostRequest);
    void deleteIngredientsById(Integer id);
    boolean isAlreadyExists(Integer id);
}
