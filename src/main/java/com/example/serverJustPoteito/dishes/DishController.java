package com.example.serverJustPoteito.dishes;

import com.example.serverJustPoteito.dishes.model.Dish;
import com.example.serverJustPoteito.dishes.repository.DishRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private DishRepository dishRepository;

    @GetMapping("/dishes")
    public ResponseEntity<Iterable<Dish>> getDishes() {
        return new ResponseEntity<>(dishRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable("id") Integer id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Empleado no encontrado.")
                );
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PostMapping("/dishes")
    public ResponseEntity<Dish> createDish() {
        return null;
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Integer> deleteDishById(@PathVariable("id") Integer id) {
        try {
            dishRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Empleado no encontrado.");
        }
    }
}
