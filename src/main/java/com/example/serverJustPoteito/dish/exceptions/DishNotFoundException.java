package com.example.serverJustPoteito.dish.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dish not found")
public class DishNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public DishNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
