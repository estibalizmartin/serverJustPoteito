package com.example.serverJustPoteito.cook.service;

import com.example.serverJustPoteito.cook.exceptions.CookNotFoundException;
import com.example.serverJustPoteito.cook.model.CookPostRequest;
import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cook.model.CookUpdateResponse;

public interface CookService {

    Iterable<CookServiceModel> getCooks();
    CookServiceModel getCookById(Integer id) throws CookNotFoundException;
    CookServiceModel createCook(CookPostRequest cookPostRequest);
    CookUpdateResponse updateCook(Integer id, CookPostRequest cookPostRequest);
    void deleteCook(Integer id);
}
