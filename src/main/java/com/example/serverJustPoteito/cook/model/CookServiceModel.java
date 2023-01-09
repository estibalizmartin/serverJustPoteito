package com.example.serverJustPoteito.cook.model;

import com.example.serverJustPoteito.dish.model.Dish;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;

public class CookServiceModel {
    private Integer id;
    private String name;
    private String last_names;
    @Temporal(TemporalType.DATE)
    private Date born_date;
    private String nationality;
    private Integer michelin_stars;
    private List<Dish> dishes;

    public CookServiceModel() {
    }

    public CookServiceModel(Integer id, String name, String last_names, Date born_date, String nationality, Integer michelin_stars, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.last_names = last_names;
        this.born_date = born_date;
        this.nationality = nationality;
        this.michelin_stars = michelin_stars;
        this.dishes = dishes;
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

    public String getLast_names() {
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getMichelin_stars() {
        return michelin_stars;
    }

    public void setMichelin_stars(Integer michelin_stars) {
        this.michelin_stars = michelin_stars;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "CookServiceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_names='" + last_names + '\'' +
                ", born_date=" + born_date +
                ", nationality='" + nationality + '\'' +
                ", michelin_stars=" + michelin_stars +
                ", dishes=" + dishes +
                '}';
    }
}
