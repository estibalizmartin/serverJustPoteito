package com.example.serverJustPoteito.cuisineType.model;

import com.example.serverJustPoteito.dish.model.DishServiceModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuisineTypeServiceModel {

    private Integer id;
    private String name;
    private String subtype;
    private String image;
    private List<DishServiceModel> dishes;

    public CuisineTypeServiceModel() {
    }

    public CuisineTypeServiceModel(Integer id, String name, String subtype, List<DishServiceModel> dishes) {
        this.id = id;
        this.name = name;
        this.subtype = subtype;
        this.dishes = dishes;
    }
    public CuisineTypeServiceModel(Integer id, String name, String subtype, List<DishServiceModel> dishes, String image) {
        this.id = id;
        this.name = name;
        this.subtype = subtype;
        this.dishes = dishes;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<DishServiceModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishServiceModel> dishes) {
        this.dishes = dishes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CuisineTypeServiceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtype='" + subtype + '\'' +
                ", subtype='" + image + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
