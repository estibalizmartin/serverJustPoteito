package com.example.serverJustPoteito.dish.service;

import com.example.serverJustPoteito.dish.model.Dish;
import com.example.serverJustPoteito.dish.model.DishPostRequest;
import com.example.serverJustPoteito.dish.model.DishUpdateResponse;

public interface DishService {
    Iterable<Dish> getDishes();
    Dish getDishById(Integer id);
    Dish createDish(DishPostRequest dishPostRequest);
    DishUpdateResponse updateDish(Integer id, DishPostRequest dishPostRequest);
    void deleteDishById(Integer id);
}
