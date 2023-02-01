package com.example.serverJustPoteito.cuisineType.service;

import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypePostRequest;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeUpdateResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CuisineTypeService {
    List<CuisineTypeServiceModel> getCuisineTypes();
    CuisineType getCuisineType(@PathVariable("id") Integer id);
    CuisineType createCuisineType(@RequestBody CuisineTypePostRequest cuisineTypePostRequest);
    CuisineTypeUpdateResponse updateCuisineType(@PathVariable("id") Integer id, @RequestBody CuisineTypePostRequest cuisineTypePostRequest);
    void deleteCuisineType(@PathVariable("id") Integer id);
}
