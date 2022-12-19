package com.example.serverJustPoteito.cuisineType.service;

import com.example.serverJustPoteito.cuisineType.model.CuisineType;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypePostRequest;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeUpdateResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CuisineTypeService {
    public Iterable<CuisineType> getCuisineTypes();
    public CuisineType getCuisineType(@PathVariable("id") Integer id);
    public CuisineType createCuisineType(@RequestBody CuisineTypePostRequest cuisineTypePostRequest);
    public CuisineTypeUpdateResponse updateCuisineType(@PathVariable("id") Integer id, @RequestBody CuisineTypePostRequest cuisineTypePostRequest);
    public void deleteCuisineType(@PathVariable("id") Integer id);
}
