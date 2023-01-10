package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.persistence.UserServiceModel;

import java.util.List;

public interface UserService {
    User signUp(User user) throws UserCantCreateException;
    List<UserServiceModel> getUsers();
    void deleteUserById(Integer id);
}
