package com.example.serverJustPoteito.ingredient.service;

import com.example.serverJustPoteito.ingredient.model.IngredientUpdateResponse;
import com.example.serverJustPoteito.ingredient.repository.IngredientRepository;
import com.example.serverJustPoteito.ingredient.model.Ingredient;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientServicelmpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Integer id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NO_CONTENT, "This ingredient doesn't exists...")
                );
    }

    @Override
    public Ingredient createIngredient(IngredientPostRequest ingredientPostRequest) {
        Ingredient ingredient = new Ingredient(
                ingredientPostRequest.getName(),
                ingredientPostRequest.getType()
        );
        return ingredientRepository.save(ingredient);
    }

    @Override
    public IngredientUpdateResponse updateIngredient(Integer id, IngredientPostRequest ingredientPostRequest) {
        boolean ingredientAlreadyExists = ingredientRepository.existsById(id);

        Ingredient ingredient = new Ingredient(
                ingredientPostRequest.getName(),
                ingredientPostRequest.getType()
        );
        ingredient = ingredientRepository.save(ingredient);

        return new IngredientUpdateResponse(ingredientAlreadyExists, ingredient);
    }

    @Override
    public void deleteIngredientsById(Integer id) {
        ingredientRepository.deleteById(id);
    }
}