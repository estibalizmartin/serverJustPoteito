package com.example.serverJustPoteito.ingredient_dish.repository;
import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface Ingredient_dishRepository extends CrudRepository<Ingredient_dish, Integer> {
    List<Ingredient_dish> findAllByIngredientIdIn(List<Integer> ingredientIds);
}
