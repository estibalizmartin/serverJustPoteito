package com.example.serverJustPoteito.ingredient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ingredient not found")
public class IngredientNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public IngredientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}