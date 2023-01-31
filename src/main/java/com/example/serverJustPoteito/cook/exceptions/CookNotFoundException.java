package com.example.serverJustPoteito.cook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cook not found")
public class CookNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public CookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
