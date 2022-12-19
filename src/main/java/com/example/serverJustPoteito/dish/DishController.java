package com.example.serverJustPoteito.dish;

import com.example.serverJustPoteito.dish.model.Dish;
import com.example.serverJustPoteito.dish.model.DishPostRequest;
import com.example.serverJustPoteito.dish.model.DishUpdateResponse;
import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.example.serverJustPoteito.dish.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dishes")
    public ResponseEntity<List<DishServiceModel>> getDishes() {
        Iterable<Dish> dishes = dishService.getDishes();

        List<DishServiceModel> response = new ArrayList<>();
        for (Dish dish : dishes) {
            response.add(new DishServiceModel(
                    dish.getId(),
                    dish.getName(),
                    dish.getPrepTime(),
                    dish.getSubtype(),
                    null,
                    dish.getCuisineTypeId()
            ));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishServiceModel> getDishById(@PathVariable("id") Integer id) {
        Dish dish = dishService.getDishById(id);

        DishServiceModel response = new DishServiceModel(
                dish.getId(),
                dish.getName(),
                dish.getPrepTime(),
                dish.getSubtype(),
                null,
                dish.getCuisineTypeId()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
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
