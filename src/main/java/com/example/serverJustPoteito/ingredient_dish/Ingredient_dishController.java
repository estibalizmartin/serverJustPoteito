package com.example.serverJustPoteito.ingredient_dish;

import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishPostRequest;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import com.example.serverJustPoteito.ingredient_dish.service.Ingredient_dishService;
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
public class Ingredient_dishController {
    @Autowired
    Ingredient_dishService ingredient_dishService;

    @GetMapping("/ingredients_dishes")
    public ResponseEntity<List<Ingredient_dishServiceModel>> getAllIngredients_dish() {
        return new ResponseEntity<>(ingredient_dishService.getAllIngredients_dish(), HttpStatus.OK);
    }

    @GetMapping("/ingredients_dishes/{id}")
    public ResponseEntity<Ingredient_dishServiceModel> getIngredient_dishById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(ingredient_dishService.getIngredient_dishById(id), HttpStatus.OK);
    }

    @PostMapping("/ingredients_dishes")
    public ResponseEntity<Ingredient_dishServiceModel> createIngredient_dish(@Valid @RequestBody Ingredient_dishPostRequest ingredient_dishPostRequest){
        return new ResponseEntity<>(
                ingredient_dishService.createIngredient_dish(ingredient_dishPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/ingredients_dishes/{id}")
    public ResponseEntity<Ingredient_dishServiceModel> updateIngredient_dish(@PathVariable("id") Integer id, @Valid @RequestBody Ingredient_dishPostRequest ingredient_dishPostRequest){
        /*IngredientUpdateResponse ingredientUpdateResponse = ingredientsServicelmp
                .updateIngredient(id, ingredientsPostRequest);*/

        if (ingredient_dishService.isAlreadyExists(id)){
            return new ResponseEntity<>(ingredient_dishService.updateIngredient_dish(id, ingredient_dishPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ingredient_dishService.updateIngredient_dish(id, ingredient_dishPostRequest), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/ingredients_dishes/{id}")
    public ResponseEntity<Integer> deleteIngredient_dishById(@PathVariable("id") Integer id){
        try {
            ingredient_dishService.deleteIngredients_dishById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This ingredient_dish doesn't exists...");
        }
    }

    // Endpoints para PMD/DI
    @GetMapping("/ingredients_dishesNoToken")
    public ResponseEntity<List<Ingredient_dishServiceModel>> getAllIngredients_dishNoToken() {
        return new ResponseEntity<>(ingredient_dishService.getAllIngredients_dish(), HttpStatus.OK);
    }

    @GetMapping("/getAllDishesByIngredientNoToken")
    public ResponseEntity<List<DishServiceModel>> getAlldishesByIngredientNoToken(@RequestParam(required = false) List<Integer> idList) {
        System.out.println(idList);
        return new ResponseEntity<>(ingredient_dishService.getAlldishesByIngredient(idList), HttpStatus.OK);
    }
}
