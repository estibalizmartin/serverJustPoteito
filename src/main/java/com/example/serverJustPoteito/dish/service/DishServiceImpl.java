package com.example.serverJustPoteito.dish.service;

import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.example.serverJustPoteito.cuisineType.repository.CuisineTypeRepository;
import com.example.serverJustPoteito.dish.model.*;
import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.dish.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CuisineTypeRepository cuisineTypeRepository;

    @Override
    public List<DishServiceModel> getDishes() {
        Iterable<Dish> dishes = dishRepository.findAll();

        List<DishServiceModel> response = new ArrayList<>();

        for (Dish dish : dishes) {
            response.add(new DishServiceModel(
                    dish.getId(),
                    dish.getName(),
                    dish.getPrepTime(),
                    dish.getSubtype(),
                    null,
                    dish.getCuisineTypeId()
            ));
        }

        return response;
    }

    @Override
    public DishServiceModel getDishById(Integer id, List<DishesExpands> expand) {
        Dish dish = dishRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Plato no encontrado.")
        );

        CuisineTypeServiceModel cuisineResponse = null;

        if (expand != null && expand.contains(DishesExpands.CUISINETYPE)) {
            CuisineType cuisineTypeDB = dish.getCuisineType();
            cuisineResponse = new CuisineTypeServiceModel(
                    cuisineTypeDB.getId(),
                    cuisineTypeDB.getName(),
                    cuisineTypeDB.getSubtype(),
                    null
            );
        }

        DishServiceModel response = new DishServiceModel(
                dish.getId(),
                dish.getName(),
                dish.getPrepTime(),
                dish.getSubtype(),
                cuisineResponse,
                dish.getCuisineTypeId()
        );

        return response;
    }

    @Override
    public DishServiceModel createDish(DishPostRequest dishPostRequest) {
        CuisineType cuisineType = cuisineTypeRepository.findById(dishPostRequest.getCuisineTypeId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Tipo de cocina no encontrado.")
        );

        Dish dish = new Dish(
                null,
                dishPostRequest.getName(),
                dishPostRequest.getPrepTime(),
                dishPostRequest.getSubtype(),
                cuisineType
        );

        dish = dishRepository.save(dish);

        DishServiceModel response = new DishServiceModel(
                dish.getId(),
                dish.getName(),
                dish.getPrepTime(),
                dish.getSubtype(),
                null,
                dish.getCuisineTypeId()
        );

        return response;
    }

    @Override
    public DishServiceModel updateDish(Integer id, DishPostRequest dishPostRequest) {
        Dish dish = new Dish(
                id,
                dishPostRequest.getName(),
                dishPostRequest.getPrepTime(),
                dishPostRequest.getSubtype(),
                null,
                dishPostRequest.getCuisineTypeId()
        );

        dish.setId(id);

        dish = dishRepository.save(dish);

        DishServiceModel response = new DishServiceModel(
                dish.getId(),
                dish.getName(),
                dish.getPrepTime(),
                dish.getSubtype(),
                null,
                dish.getCuisineTypeId()
        );

        return response;
    }

    @Override
    public void deleteDishById(Integer id) {
        dishRepository.deleteById(id);
    }

    public boolean isAlreadyExists(Integer id) {
        return dishRepository.existsById(id);
    }
}
