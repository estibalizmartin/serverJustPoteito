package com.example.serverJustPoteito.ingredient.repository;

import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    List<Ingredient> findAllByIdIn(List<Integer> ids);

    @Transactional
    @Query(value = "select * from ingredients where id in (Select ingredient_id from ingredient_dish i where i.dish_id = :dishId)", nativeQuery = true)
    Iterable<Ingredient> findAllByDishId(@Param("dishId") Integer dishId);
}
