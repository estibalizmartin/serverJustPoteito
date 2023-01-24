package com.example.serverJustPoteito.ingredient_dish.service;

import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishPostRequest;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;

import java.util.List;

public interface Ingredient_dishService {
    List<Ingredient_dishServiceModel> getAllIngredients_dish();
    Ingredient_dishServiceModel getIngredient_dishById(Integer id);
    Ingredient_dishServiceModel createIngredient_dish(Ingredient_dishPostRequest ingredients_dishPostRequest);
    Ingredient_dishServiceModel updateIngredient_dish(Integer id, Ingredient_dishPostRequest ingredients_dishPostRequest);
    void deleteIngredients_dishById(Integer id);
    List<DishServiceModel> getAlldishesByIngredient(List<Integer> ingredientIds);
    boolean isAlreadyExists(Integer id);
}
