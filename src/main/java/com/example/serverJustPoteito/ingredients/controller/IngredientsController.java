package com.example.serverJustPoteito.ingredients.controller;

import com.example.serverJustPoteito.ingredients.model.IngredientUpdateResponse;
import com.example.serverJustPoteito.ingredients.model.Ingredients;
import com.example.serverJustPoteito.ingredients.model.IngredientsPostRequest;
import com.example.serverJustPoteito.ingredients.service.IngredientsServicelmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class IngredientsController {
    @Autowired
    IngredientsServicelmp ingredientsServicelmp;

    @GetMapping("/com/example/serverJustPoteito/ingredients")
    public ResponseEntity<Iterable<Ingredients>> getAllIngredients() {
        return new ResponseEntity<Iterable<Ingredients>>(ingredientsServicelmp.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/com/example/serverJustPoteito/ingredients/{id}")
    public ResponseEntity<Ingredients> getIngredientById(@PathVariable ("id") int id){
        return new ResponseEntity<>(ingredientsServicelmp.getIngredientById(id), HttpStatus.OK);
    }

    @PostMapping("/com/example/serverJustPoteito/ingredients")
    public ResponseEntity<Ingredients> createIngredient(@RequestBody IngredientsPostRequest ingredientsPostRequest){
        return new ResponseEntity<>(
                ingredientsServicelmp.createIngredient(ingredientsPostRequest), HttpStatus.OK);
    }

    @PutMapping("/com/example/serverJustPoteito/ingredients/{id}")
    public ResponseEntity<Ingredients> updateIngredient(@PathVariable ("id") int id, @RequestBody IngredientsPostRequest ingredientsPostRequest){
        IngredientUpdateResponse ingredientUpdateResponse = ingredientsServicelmp
                .updateIngredient(id, ingredientsPostRequest);

        if (ingredientUpdateResponse.isIngredientAlreadyExists()){
            return new ResponseEntity<>(ingredientUpdateResponse.getIngredients(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ingredientUpdateResponse.getIngredients(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/com/example/serverJustPoteito/ingredients/{id}")
    public ResponseEntity<Integer> deleteIngredientById(@PathVariable ("id") Integer id){
        try {
            ingredientsServicelmp.deleteIngredientsById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This ingredient doesn't exists...");
        }
    }
}
