package com.example.serverJustPoteito.ingredient.service;

import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient.model.IngredientPostRequest;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.repository.IngredientRepository;
import com.example.serverJustPoteito.ingredient_dish.model.Ingredient_dishServiceModel;
import com.example.serverJustPoteito.ingredient_dish.service.Ingredient_dishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientServicelmpl implements IngredientService{
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private Ingredient_dishService ingredient_dishService;

    @Override
    public List<IngredientServiceModel> getAllIngredients() {

        Iterable<Ingredient> ingredient = ingredientRepository.findAll();

        List<IngredientServiceModel> response = new ArrayList<>();

        for (Ingredient ingredients : ingredient) {
            response.add(new IngredientServiceModel(
                    ingredients.getId(),
                    ingredients.getName(),
                    ingredients.getType()
            ));
        }
        return response;
    }

    @Override
    public IngredientServiceModel getIngredientById(Integer id) {
        Ingredient ingredients = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NO_CONTENT, "This ingredient doesn't exists...")
                );

        IngredientServiceModel response = new IngredientServiceModel(
                ingredients.getId(),
                ingredients.getName(),
                ingredients.getType()
        );

        return response;
    }

    @Override
    public List<IngredientServiceModel> getAllByDishId(Integer dishId) {

        List<IngredientServiceModel> ingDish = ingredient_dishService.getAllIngredientsByDishId(dishId);
        return ingDish;
        // System.out.println(ingDish);
        /*
        Iterable<Ingredient> ingredients = ingredientRepository.findAllByDishId(dishId);
        List<IngredientServiceModel> response = new ArrayList<>();
        String amount;

        for (Ingredient ingredient : ingredients) {
            //amount = ingredient_dishService.getAmount(dishId, ingredient.getId());
            response.add(new IngredientServiceModel(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getType()
                    //amount
            ));
        }
        return response;

         */
    }

    @Override
    public List<Ingredient> getAllIngredientById(List<Integer> listId) {
        List<Ingredient> ingredientList = ingredientRepository.findAllByIdIn(listId);
        return ingredientList;
    }

    @Override
    public IngredientServiceModel createIngredient(IngredientPostRequest ingredientsPostRequest) {
        Ingredient ingredient = new Ingredient(
                ingredientsPostRequest.getName(),
                ingredientsPostRequest.getType()
        );

        ingredient = ingredientRepository.save(ingredient);

        IngredientServiceModel response = new IngredientServiceModel(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType()
        );

        return response;
    }

    @Override
    public IngredientServiceModel updateIngredient(Integer id, IngredientPostRequest ingredientPostRequest) {

        Ingredient ingredient = new Ingredient(
                ingredientPostRequest.getName(),
                ingredientPostRequest.getType()
        );

        ingredient = ingredientRepository.save(ingredient);

        IngredientServiceModel response = new IngredientServiceModel(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType()
        );

        return response;
    }

    @Override
    public void deleteIngredientsById(Integer id) {
        ingredientRepository.deleteById(id);
    }

    public boolean isAlreadyExists(Integer id){
        return ingredientRepository.existsById(id);
    }
}