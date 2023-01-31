package com.example.serverJustPoteito.ingredient_dish.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "IngrendientDish not found")
public class IngredientDishNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public IngredientDishNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}