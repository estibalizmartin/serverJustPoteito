package com.example.serverJustPoteito.cook.service;

import com.example.serverJustPoteito.cook.model.CookPostRequest;
import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cook.model.CookUpdateResponse;
import com.example.serverJustPoteito.dish.model.DishServiceModel;

import java.util.List;

public interface CookService {

    Iterable<CookServiceModel> getCooks();
    CookServiceModel getCookById(Integer id);
    CookServiceModel createCook(CookPostRequest cookPostRequest);
    CookUpdateResponse updateCook(Integer id, CookPostRequest cookPostRequest);
    void deleteCook(Integer id);
    List<DishServiceModel>getDishesByCookId(Integer cookId);
}
