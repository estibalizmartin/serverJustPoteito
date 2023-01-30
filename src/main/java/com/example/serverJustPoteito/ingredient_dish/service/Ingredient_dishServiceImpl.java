package com.example.serverJustPoteito.ingredient_dish.service;

import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.example.serverJustPoteito.dish.repository.DishRepository;
import com.example.serverJustPoteito.dish.service.DishService;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient.repository.IngredientRepository;
import com.example.serverJustPoteito.ingredient.service.IngredientService;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishPostRequest;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import com.example.serverJustPoteito.ingredient_dish.repository.Ingredient_dishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Ingredient_dishServiceImpl implements Ingredient_dishService{
    @Autowired
    private Ingredient_dishRepository ingredient_dishRepository;
    @Autowired
    private DishService dishService;

//    @Autowired
//    private IngredientService ingredientService;
    @Override
    public List<Ingredient_dishServiceModel> getAllIngredients_dish() {
        /*
        Iterable<Ingredient_dish> ingredient_dish = ingredient_dishRepository.findAll();

        List<Ingredient_dishServiceModel> response = new ArrayList<>();

        for (Ingredient_dish ingredients_dish : ingredient_dish) {
            response.add(new Ingredient_dishServiceModel(
                    ingredients_dish.getIngredient().getId(),
                    ingredients_dish.getDish().getId(),
                    ingredients_dish.getAmount()
            ));
        }
        return response;
         */
        return null;
    }

    @Override
    public List<IngredientServiceModel> getAllIngredientsByDishId(Integer id) {
        Iterable<Ingredient_dish> ingredients_dish = ingredient_dishRepository.findAllByDishId(id);

        List<IngredientServiceModel> response = new ArrayList<>();

        for (Ingredient_dish ingredient_dish : ingredients_dish) {

            Ingredient ingredient = ingredient_dish.getIngredient();
            IngredientServiceModel item = new IngredientServiceModel(ingredient.getId(), ingredient.getName(), ingredient.getType(), ingredient_dish.getAmount());
            response.add(item);
        }
        return response;
    }


    @Override
    public Ingredient_dishServiceModel getIngredient_dishById(Integer id) {
        return null;
    }

    @Override
    public Ingredient_dishServiceModel createIngredient_dish(Ingredient_dishPostRequest ingredients_dishPostRequest) {
        return null;
    }

    @Override
    public Ingredient_dishServiceModel updateIngredient_dish(Integer id, Ingredient_dishPostRequest ingredients_dishPostRequest) {
        return null;
    }

    @Override
    public void deleteIngredients_dishById(Integer id) {

    }

    @Override
    public List<DishServiceModel> getAlldishesByIngredient(List<Integer> ingredientIds) {
        List<Ingredient_dish> ingredient_dish = ingredient_dishRepository.findAllByIngredientIdIn(ingredientIds);

        List<Ingredient_dishServiceModel> response = new ArrayList<>();

        for (Ingredient_dish ingredients_dish : ingredient_dish) {
            response.add(new Ingredient_dishServiceModel(
                    ingredients_dish.getId().getIngredientId(),
                    ingredients_dish.getId().getDishId(),
                    ingredients_dish.getAmount()
            ));
        }
        System.out.println(response);
        List<Integer> dishIds = new ArrayList<>();
        for (Ingredient_dishServiceModel object: response) {
            dishIds.add(object.getDishId());
        }

        return dishService.getAlldishesByIngredient(dishIds);
    }

    @Override
    public boolean isAlreadyExists(Integer id) {
        return false;
    }
}
