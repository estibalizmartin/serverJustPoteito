package com.example.serverJustPoteito.dish.repository;

import com.example.serverJustPoteito.dish.model.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Integer> {
}
