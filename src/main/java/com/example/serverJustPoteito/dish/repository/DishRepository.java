package com.example.serverJustPoteito.dish.repository;

import com.example.serverJustPoteito.dish.persistence.Dish;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findByCuisineTypeId(int cuisineTypeId);

}
