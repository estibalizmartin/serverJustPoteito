package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.persistence.User;

public interface UserService {
    User signUp(User user) throws UserCantCreateException;
}
