package com.example.serverJustPoteito.ingredients.model;

public class IngredientUpdateResponse {

    private  boolean ingredientAlreadyExists;
    private Ingredients ingredients;

    public IngredientUpdateResponse() {
    }

    public IngredientUpdateResponse(boolean ingredientAlreadyExists, Ingredients ingredients) {
        this.ingredientAlreadyExists = ingredientAlreadyExists;
        this.ingredients = ingredients;
    }

    public boolean isIngredientAlreadyExists() {
        return ingredientAlreadyExists;
    }

    public void setIngredientAlreadyExists(boolean ingredientAlreadyExists) {
        this.ingredientAlreadyExists = ingredientAlreadyExists;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "IngredientUpdateResponse{" +
                "ingredientAlreadyExists=" + ingredientAlreadyExists +
                ", ingredients=" + ingredients +
                '}';
    }
}