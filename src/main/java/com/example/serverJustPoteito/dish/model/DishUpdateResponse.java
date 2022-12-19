package com.example.serverJustPoteito.dish.model;

public class DishUpdateResponse {

    private boolean dishAlreadyExists;
    private Dish dish;

    public DishUpdateResponse() {
    }

    public DishUpdateResponse(boolean dishAlreadyExists, Dish dish) {
        this.dishAlreadyExists = dishAlreadyExists;
        this.dish = dish;
    }

    public boolean isDishAlreadyExists() {
        return dishAlreadyExists;
    }

    public void setDishAlreadyExists(boolean dishAlreadyExists) {
        this.dishAlreadyExists = dishAlreadyExists;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "DishUpdateResponse{" +
                "dishAlreadyExists=" + dishAlreadyExists +
                ", dish=" + dish +
                '}';
    }
}
