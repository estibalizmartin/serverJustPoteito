package com.example.serverJustPoteito.auth.service;

import com.example.serverJustPoteito.auth.exceptions.UserCantCreateException;
import com.example.serverJustPoteito.auth.model.PasswordPostRequest;
import com.example.serverJustPoteito.auth.model.UserPostRequest;
import com.example.serverJustPoteito.auth.persistence.User;
import com.example.serverJustPoteito.auth.model.UserServiceModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User signUp(User user) throws UserCantCreateException;
    User signUpCrypted(User user) throws UserCantCreateException;
    List<UserServiceModel> getUsers();
    UserServiceModel logUser(@RequestBody String email, String password);
    boolean sendEmail(String email);
    UserServiceModel getUserById(Integer id);
    UserServiceModel createUser(UserPostRequest userPostRequest);
    UserServiceModel updateUser(Integer id, UserPostRequest userPostRequest);
    void deleteUserById(Integer id);
    int changeUserPasswordNoToken(PasswordPostRequest passwordPostRequest);
    boolean isAlreadyExists(Integer id);
}
