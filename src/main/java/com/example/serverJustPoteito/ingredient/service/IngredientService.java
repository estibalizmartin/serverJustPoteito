package com.example.serverJustPoteito.ingredient.service;

import com.example.serverJustPoteito.ingredient.model.IngredientUpdateResponse;
import com.example.serverJustPoteito.ingredient.model.Ingredient;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;

public interface IngredientService {
    Iterable<Ingredient> getAllIngredients();
    Ingredient getIngredientById(Integer id);
    Ingredient createIngredient(IngredientPostRequest ingredientPostRequest);
    IngredientUpdateResponse updateIngredient(Integer id, IngredientPostRequest ingredientPostRequest);
    void deleteIngredientsById(Integer id);

    //TODO BUSCAR POR ID DE PLATO
    /*
    Ver video de Mikel para las relaciones N:M
        Ingredientes por id de plato =
        tabla PlatoIngredientes
        Integer idPlato
        Integer idIngredientes
        Float? cantidad
     */
}
