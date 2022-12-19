package com.example.serverJustPoteito.dish.service;

import com.example.serverJustPoteito.dish.model.Dish;
import com.example.serverJustPoteito.dish.model.DishPostRequest;
import com.example.serverJustPoteito.dish.model.DishUpdateResponse;
import com.example.serverJustPoteito.dish.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Iterable<Dish> getDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getDishById(Integer id) {
        return dishRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Plato no encontrado.")
                );
    }

    @Override
    public Dish createDish(DishPostRequest dishPostRequest) {
        Dish dish = new Dish(
                dishPostRequest.getName(),
                dishPostRequest.getPrepTime(),
                dishPostRequest.getSubtype()
        );
        return dishRepository.save(dish);
    }

    @Override
    public DishUpdateResponse updateDish(Integer id, DishPostRequest dishPostRequest) {

        boolean dishAlreadyExists = dishRepository.existsById(id);

        Dish dish = new Dish(
                id,
                dishPostRequest.getName(),
                dishPostRequest.getPrepTime(),
                dishPostRequest.getSubtype()
        );

        dish = dishRepository.save(dish);

        return new DishUpdateResponse(dishAlreadyExists, dish);
    }

    @Override
    public void deleteDishById(Integer id) {
        dishRepository.deleteById(id);
    }
}
