package com.example.serverJustPoteito.ingredient;

import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.service.IngredientService;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientServiceModel>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/ingredientsByDishIdNoToken/{dishId}")
    public ResponseEntity<List<IngredientServiceModel>> getAllIngredientsByDishId(@PathVariable("dishId") Integer dishId) {
        return new ResponseEntity<>(ingredientService.getAllByDishId(dishId), HttpStatus.OK);
    }
    @GetMapping("/ingredientsNoToken")
    public ResponseEntity<List<IngredientServiceModel>> getAllIngredientsNoToken() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<IngredientServiceModel> getIngredientById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(ingredientService.getIngredientById(id), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity<IngredientServiceModel> createIngredient(@Valid @RequestBody IngredientPostRequest ingredientPostRequest){
        return new ResponseEntity<>(
                ingredientService.createIngredient(ingredientPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<IngredientServiceModel> updateIngredient(@PathVariable("id") Integer id, @Valid @RequestBody IngredientPostRequest ingredientPostRequest){
        /*IngredientUpdateResponse ingredientUpdateResponse = ingredientsServicelmp
                .updateIngredient(id, ingredientsPostRequest);*/

        if (ingredientService.isAlreadyExists(id)){
            return new ResponseEntity<>(ingredientService.updateIngredient(id, ingredientPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ingredientService.updateIngredient(id, ingredientPostRequest), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Integer> deleteIngredientById(@PathVariable("id") Integer id){
        try {
            ingredientService.deleteIngredientsById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This ingredient doesn't exists...");
        }
    }
}
