package com.example.serverJustPoteito.cuisineType.model;

public class CuisineTypePostRequest {
    private String name;
    private String subtype;
    public CuisineTypePostRequest() {
    }

    public CuisineTypePostRequest(String name, String subtype) {
        this.name = name;
        this.subtype = subtype;
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

    @Override
    public String toString() {
        return "CuisineType{" +
                "name='" + name + '\'' +
                ", subtype='" + subtype + '\'' +
                '}';
    }
}