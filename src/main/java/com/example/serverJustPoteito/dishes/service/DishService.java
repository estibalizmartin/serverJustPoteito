package com.example.serverJustPoteito.dishes.service;

import com.example.serverJustPoteito.dishes.model.Dish;
import com.example.serverJustPoteito.dishes.model.DishPostRequest;
import com.example.serverJustPoteito.dishes.model.DishUpdateResponse;

public interface DishService {
    Iterable<Dish> getDishes();
    Dish getDishById(Integer id);
    Dish createDish(DishPostRequest dishPostRequest);
    DishUpdateResponse updateDish(Integer id, DishPostRequest dishPostRequest);
    void deleteDishById(Integer id);
}
