package com.example.serverJustPoteito.dish;

import com.example.serverJustPoteito.cuisineType.service.CuisineTypeService;
import com.example.serverJustPoteito.dish.exceptions.DishNotFoundException;
import com.example.serverJustPoteito.dish.model.*;
import com.example.serverJustPoteito.dish.service.DishService;
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
    public ResponseEntity<?> getDishes() {
        List<DishServiceModel> dishes = dishService.getDishes();

        if (dishes != null) {
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        } else {
            return ResponseEntity.status(513).body("No se han podido cargar los platos");
        }
    }

    @GetMapping("/dishesNoToken")
    public ResponseEntity<?> getDishesNoToken() {
        List<DishServiceModel> dishes = dishService.getDishes();

        if (dishes != null) {
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        } else {
            return ResponseEntity.status(513).body("No se han podido cargar los platos");
        }
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishServiceModel> getDishById(@PathVariable("id") Integer id,
                                                        @RequestParam(required = false) List<DishesExpands> expand) {
        try {
            return new ResponseEntity<>(dishService.getDishById(id, expand), HttpStatus.OK);
        } catch (DishNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
        }
    }

    @GetMapping("/dishesNoToken/{id}")
    public ResponseEntity<DishServiceModel> getDishByIdNoToken(@PathVariable("id") Integer id,
                                                               @RequestParam(required = false) List<DishesExpands> expand) {
        try {
            return new ResponseEntity<>(dishService.getDishById(id, expand), HttpStatus.OK);
        } catch (DishNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
        }
    }

    @PostMapping("/dishes")
    public ResponseEntity<?> createDish(@Valid @RequestBody DishPostRequest dishPostRequest) {
        return new ResponseEntity<>(dishService.createDish(dishPostRequest), HttpStatus.CREATED);

        DishServiceModel createdDish = dishService.createDish(dishPostRequest);

        if (createdDish != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(createdDish);
        } else {
            return ResponseEntity.status(514).body("No se puedo añadir el plato");
        }
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

    @GetMapping("/dishesByCookNoToken/{cookId}")
    public ResponseEntity<List<DishServiceModel>> getDishesByCook(@PathVariable("cookId") Integer cookId) {
        return new ResponseEntity<>(dishService.getDishesByCook(cookId), HttpStatus.OK);
    }
}
