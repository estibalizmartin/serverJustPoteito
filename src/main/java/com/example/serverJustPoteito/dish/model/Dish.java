package com.example.serverJustPoteito.dish.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(length = 70)
    private String name;
    @Column
    private Integer prepTime;
//    private String allergen;
    @Column(length = 70)
    private String subtype;
    @Column(length = 70)
    private String cuisineTypeId;

    public Dish() {
    }

    public Dish(String name, Integer prepTime, String subtype, String cuisineTypeId) {
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeId = cuisineTypeId;
    }

    public Dish(Integer id, String name, Integer prepTime, String subtype, String cuisineTypeId) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineTypeId = cuisineTypeId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCuisineTypeId() {
        return cuisineTypeId;
    }

    public void setCuisineTypeId(String cuisineTypeId) {
        this.cuisineTypeId = cuisineTypeId;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", cuisineTypeId='" + cuisineTypeId + '\'' +
                '}';
    }
}
