package com.example.serverJustPoteito.ingredients.service;

import com.example.serverJustPoteito.ingredients.model.Ingredients;
import com.example.serverJustPoteito.ingredients.model.IngredientsPostRequest;
import com.example.serverJustPoteito.ingredients.model.IngredientsServiceModel;
import com.example.serverJustPoteito.ingredients.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientsServicelmp implements IngredientsService{

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Override
    public List<IngredientsServiceModel> getAllIngredients() {

        Iterable<Ingredients> ingredients = ingredientsRepository.findAll();

        List<IngredientsServiceModel> response = new ArrayList<>();

        for (Ingredients ingredient : ingredients) {
            response.add(new IngredientsServiceModel(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getType()
            ));
        }
        return response;
    }

    @Override
    public IngredientsServiceModel getIngredientById(Integer id) {
         Ingredients ingredients = ingredientsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NO_CONTENT, "This ingredient doesn't exists...")
                );

         IngredientsServiceModel response = new IngredientsServiceModel(
                 ingredients.getId(),
                 ingredients.getName(),
                 ingredients.getType()
         );

         return response;
    }

    @Override
    public IngredientsServiceModel createIngredient(IngredientsPostRequest ingredientsPostRequest) {
        Ingredients ingredients = new Ingredients(
                ingredientsPostRequest.getName(),
                ingredientsPostRequest.getType()
        );

        ingredients = ingredientsRepository.save(ingredients);

        IngredientsServiceModel response = new IngredientsServiceModel(
                ingredients.getId(),
                ingredients.getName(),
                ingredients.getType()
        );

        return response;
    }

    @Override
    public IngredientsServiceModel updateIngredient(Integer id, IngredientsPostRequest ingredientsPostRequest) {

        Ingredients ingredients = new Ingredients(
                ingredientsPostRequest.getName(),
                ingredientsPostRequest.getType()
        );

        ingredients = ingredientsRepository.save(ingredients);

        IngredientsServiceModel response = new IngredientsServiceModel(
                ingredients.getId(),
                ingredients.getName(),
                ingredients.getType()
        );

        return response;
    }

    @Override
    public void deleteIngredientsById(Integer id) {
        ingredientsRepository.deleteById(id);
    }

    public boolean isAlreadyExists(Integer id){
        return ingredientsRepository.existsById(id);
    }
}