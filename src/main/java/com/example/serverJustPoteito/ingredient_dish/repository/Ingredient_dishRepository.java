package com.example.serverJustPoteito.ingredient_dish.repository;
import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface Ingredient_dishRepository extends CrudRepository<Ingredient_dish, Integer> {
    List<Ingredient_dish> findAllByIngredientIdIn(List<Integer> ingredientIds);
    /*
    @Transactional
    @Query(value = "select amount from ingredient_dish where dish_id = ?1 and ingredient_id = ?2 ", nativeQuery = true)
    String findAmount(Integer dishId, Integer ingredientId);*/
    List<Ingredient_dish> findAllByDishId(Integer dishIds);
}
