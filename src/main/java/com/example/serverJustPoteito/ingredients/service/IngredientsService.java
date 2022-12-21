package com.example.serverJustPoteito.ingredients.service;

import com.example.serverJustPoteito.ingredients.model.IngredientUpdateResponse;
import com.example.serverJustPoteito.ingredients.model.Ingredients;
import com.example.serverJustPoteito.ingredients.model.IngredientsPostRequest;

public interface IngredientsService {
    Iterable<Ingredients> getAllIngredients();
    Ingredients getIngredientById(Integer id);
    Ingredients createIngredient(IngredientsPostRequest ingredientsPostRequest);
    IngredientUpdateResponse updateIngredient(Integer id, IngredientsPostRequest ingredientsPostRequest);
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
