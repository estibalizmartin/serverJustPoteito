package com.example.serverJustPoteito.dish.service;

import com.example.serverJustPoteito.dish.exceptions.DishNotFoundException;
import com.example.serverJustPoteito.dish.model.*;

import java.util.List;

public interface DishService {
    List<DishServiceModel> getDishes();
    DishServiceModel getDishById(Integer id, List<DishesExpands> expand) throws DishNotFoundException;
    DishServiceModel createDish(DishPostRequest dishPostRequest);
    DishServiceModel updateDish(Integer id, DishPostRequest dishPostRequest);
    void deleteDishById(Integer id);
    boolean isAlreadyExists(Integer id);
    List<DishServiceModel> getDishesByCuisineType(Integer cuisineTypeId);
    List<DishServiceModel> getDishesByCook(Integer cookId);
    List<DishServiceModel> findByDishListIds(List<Integer> dishesIds);
    List<DishServiceModel> getAlldishesByIngredient(List<Integer> ingredientIds);
}
