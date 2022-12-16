package com.example.serverJustPoteito.dishes;

import com.example.serverJustPoteito.dishes.model.Dish;
import com.example.serverJustPoteito.dishes.model.DishPostRequest;
import com.example.serverJustPoteito.dishes.model.DishUpdateResponse;
import com.example.serverJustPoteito.dishes.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dishes")
    public ResponseEntity<Iterable<Dish>> getDishes() {
        return new ResponseEntity<>(dishService.getDishes(), HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(dishService.getDishById(id), HttpStatus.OK);
    }

    @PostMapping("/dishes")
    public ResponseEntity<Dish> createDish(@Valid @RequestBody DishPostRequest dishPostRequest) {
        return new ResponseEntity<>(dishService.createDish(dishPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<Dish> updateDish(
            @PathVariable("id") Integer id,
            @Valid @RequestBody DishPostRequest dishPostRequest) {

        DishUpdateResponse dishUpdateResponse = dishService.updateDish(id, dishPostRequest);

        if (dishUpdateResponse.isDishAlreadyExists()) {
            return new ResponseEntity<>(dishUpdateResponse.getDish(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(dishUpdateResponse.getDish(), HttpStatus.CREATED);
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
}
