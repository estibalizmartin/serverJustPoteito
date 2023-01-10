package com.example.serverJustPoteito.ingredient;

import com.example.serverJustPoteito.ingredient.model.IngredientUpdateResponse;
import com.example.serverJustPoteito.ingredient.service.IngredientServicelmpl;
import com.example.serverJustPoteito.ingredient.model.Ingredient;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class IngredientController {
    @Autowired
    IngredientServicelmpl ingredientsServicelmp;

    @GetMapping("/ingredients")
    public ResponseEntity<Iterable<Ingredient>> getAllIngredients() {
        return new ResponseEntity<Iterable<Ingredient>>(ingredientsServicelmp.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable ("id") int id){
        return new ResponseEntity<>(ingredientsServicelmp.getIngredientById(id), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientPostRequest ingredientPostRequest){
        return new ResponseEntity<>(
                ingredientsServicelmp.createIngredient(ingredientPostRequest), HttpStatus.OK);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable ("id") int id, @RequestBody IngredientPostRequest ingredientPostRequest){
        IngredientUpdateResponse ingredientUpdateResponse = ingredientsServicelmp
                .updateIngredient(id, ingredientPostRequest);

        if (ingredientUpdateResponse.isIngredientAlreadyExists()){
            return new ResponseEntity<>(ingredientUpdateResponse.getIngredients(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ingredientUpdateResponse.getIngredients(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Integer> deleteIngredientById(@PathVariable ("id") Integer id){
        try {
            ingredientsServicelmp.deleteIngredientsById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This ingredient doesn't exists...");
        }
    }
}
