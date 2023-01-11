package com.example.serverJustPoteito.cuisineType.model;

import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;

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
