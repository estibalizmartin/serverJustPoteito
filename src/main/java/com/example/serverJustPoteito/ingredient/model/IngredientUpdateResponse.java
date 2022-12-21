package com.example.serverJustPoteito.ingredient.model;

public class IngredientUpdateResponse {

    private  boolean ingredientAlreadyExists;
    private Ingredient ingredient;

    public IngredientUpdateResponse() {
    }

    public IngredientUpdateResponse(boolean ingredientAlreadyExists, Ingredient ingredient) {
        this.ingredientAlreadyExists = ingredientAlreadyExists;
        this.ingredient = ingredient;
    }

    public boolean isIngredientAlreadyExists() {
        return ingredientAlreadyExists;
    }

    public void setIngredientAlreadyExists(boolean ingredientAlreadyExists) {
        this.ingredientAlreadyExists = ingredientAlreadyExists;
    }

    public Ingredient getIngredients() {
        return ingredient;
    }

    public void setIngredients(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "IngredientUpdateResponse{" +
                "ingredientAlreadyExists=" + ingredientAlreadyExists +
                ", ingredients=" + ingredient +
                '}';
    }
}