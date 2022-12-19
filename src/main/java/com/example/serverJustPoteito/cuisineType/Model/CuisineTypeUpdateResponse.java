package com.example.serverJustPoteito.cuisineType.Model;

import com.example.serverJustPoteito.cuisineType.Model.CuisineType;

public class CuisineTypeUpdateResponse {

    private boolean alreadyExists;
    private CuisineType updatedCuisine;

    public CuisineTypeUpdateResponse() {
    }

    public CuisineTypeUpdateResponse(boolean alreadyExists, CuisineType updatedCuisine) {
        this.alreadyExists = alreadyExists;
        this.updatedCuisine = updatedCuisine;
    }

    public boolean isAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public CuisineType getUpdatedCuisine() {
        return updatedCuisine;
    }

    public void setUpdatedCuisine(CuisineType updatedCuisine) {
        this.updatedCuisine = updatedCuisine;
    }
}
