package com.example.serverJustPoteito.dish;

import com.example.serverJustPoteito.cuisineType.service.CuisineTypeService;
import com.example.serverJustPoteito.dish.model.*;
import com.example.serverJustPoteito.dish.service.DishService;
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
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private CuisineTypeService cuisineTypeService;

    @GetMapping("/dishes")
    public ResponseEntity<List<DishServiceModel>> getDishes() {
        return new ResponseEntity<>(dishService.getDishes(), HttpStatus.OK);
    }
    @GetMapping("/dishesNoToken")
    public ResponseEntity<List<DishServiceModel>> getDishesNoToken() {
        return new ResponseEntity<>(dishService.getDishes(), HttpStatus.OK);
    }

    /*@GetMapping("/dishes/{id}")
    public ResponseEntity<DishServiceModel> getDishById(@PathVariable("id") Integer id,
                                                        @RequestParam(required = false) List<DishesExpands> expand) {
        return new ResponseEntity<>(dishService.getDishById(id, expand), HttpStatus.OK);
    }*/

    @PostMapping("/dishes")
    public ResponseEntity<DishServiceModel> createDish(@Valid @RequestBody DishPostRequest dishPostRequest) {
        return new ResponseEntity<>(dishService.createDish(dishPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<DishServiceModel> updateDish(
            @PathVariable("id") Integer id,
            @Valid @RequestBody DishPostRequest dishPostRequest) {
        if (dishService.isAlreadyExists(id)) {
            return new ResponseEntity<>(dishService.updateDish(id, dishPostRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(dishService.updateDish(id, dishPostRequest), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Integer> deleteDishById(@PathVariable("id") Integer id) {
        try {
            dishService.deleteDishById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Plato no encontrado.");
        }
    }
    @GetMapping("/dishesByCuisineTypeNoToken/{cuisineTypeId}")
    public ResponseEntity<List<DishServiceModel>> getDishesByCuisineType(@PathVariable("cuisineTypeId") Integer cuisineTypeId) {
        return new ResponseEntity<>(dishService.getDishesByCuisineType(cuisineTypeId), HttpStatus.OK);
    }
    /*@GetMapping("/dishesByIngredientNoToken/{cuisineTypeId}")
    public ResponseEntity<List<DishServiceModel>> getDishesByIngredient(@PathVariable("ingredientList") List<Integer> ingredientList) {
        return new ResponseEntity<>(dishService.getDishesByIngredient(ingredientList), HttpStatus.OK);
    }*/
}
