package com.example.serverJustPoteito.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Couldn´t create user")

public class UserCantCreateException extends Exception{
    public UserCantCreateException(String errorMessage) {
        super(errorMessage);
    }
}
