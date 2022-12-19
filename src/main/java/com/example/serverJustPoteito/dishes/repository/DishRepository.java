package com.example.serverJustPoteito.dishes.repository;

import com.example.serverJustPoteito.dishes.model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Integer> {
}
