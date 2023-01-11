package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.AuthRequest;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.model.UserServiceModel;

import java.util.List;

public interface UserService {
    User signUp(User user) throws UserCantCreateException;
    List<UserServiceModel> getUsers();
    void deleteUserById(Integer id);
    UserServiceModel createUser(AuthRequest authRequest);
    UserServiceModel updateUser(Integer id, AuthRequest authRequest);
    boolean isAlreadyExists(Integer id);
}
