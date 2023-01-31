package com.example.serverJustPoteito.ingredient.service;

import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;

import java.util.List;

public interface IngredientService {
    List<IngredientServiceModel> getAllIngredients();
    IngredientServiceModel getIngredientById(Integer id);
    List<IngredientServiceModel> getAllByDishId(Integer dishId);
    List<Ingredient> getAllIngredientById(List<Integer> listId);
    IngredientServiceModel createIngredient(IngredientPostRequest ingredientsPostRequest);
    IngredientServiceModel updateIngredient(Integer id, IngredientPostRequest ingredientsPostRequest);
    void deleteIngredientsById(Integer id);
    boolean isAlreadyExists(Integer id);
}
