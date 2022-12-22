package com.example.serverJustPoteito.ingredients.controller;

import com.example.serverJustPoteito.ingredients.model.IngredientsPostRequest;
import com.example.serverJustPoteito.ingredients.model.IngredientsServiceModel;
import com.example.serverJustPoteito.ingredients.service.IngredientsServicelmp;
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
public class IngredientsController {
    @Autowired
    IngredientsServicelmp ingredientsService;

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientsServiceModel>> getAllIngredients() {
        return new ResponseEntity<>(ingredientsService.getAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<IngredientsServiceModel> getIngredientById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(ingredientsService.getIngredientById(id), HttpStatus.OK);
    }

    @PostMapping("/ingredients")
    public ResponseEntity<IngredientsServiceModel> createIngredient(@Valid @RequestBody IngredientsPostRequest ingredientsPostRequest){
        return new ResponseEntity<>(
                ingredientsService.createIngredient(ingredientsPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<IngredientsServiceModel> updateIngredient(@PathVariable("id") Integer id, @Valid @RequestBody IngredientsPostRequest ingredientsPostRequest){
        /*IngredientUpdateResponse ingredientUpdateResponse = ingredientsServicelmp
                .updateIngredient(id, ingredientsPostRequest);*/

        if (ingredientsService.isAlreadyExists(id)){
            return new ResponseEntity<>(ingredientsService.updateIngredient(id, ingredientsPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ingredientsService.updateIngredient(id, ingredientsPostRequest), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Integer> deleteIngredientById(@PathVariable("id") Integer id){
        try {
            ingredientsService.deleteIngredientsById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This ingredient doesn't exists...");
        }
    }
}
