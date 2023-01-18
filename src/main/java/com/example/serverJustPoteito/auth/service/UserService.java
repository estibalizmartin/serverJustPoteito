package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.Exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.UserPostRequest;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.model.UserServiceModel;

import java.util.List;

public interface UserService {
    User signUp(User user) throws UserCantCreateException;
    List<UserServiceModel> getUsers(int limit, int offset);
    UserServiceModel getUserById(Integer id);
    UserServiceModel createUser(UserPostRequest userPostRequest);
    UserServiceModel updateUser(Integer id, UserPostRequest userPostRequest);
    void deleteUserById(Integer id);
    boolean isAlreadyExists(Integer id);
}
