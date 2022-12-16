package com.example.serverJustPoteito.cuisineType.Model;

public class CuisineTypePostRequest {
    private Integer id;
    private String name;
    private String subtype;
    public CuisineTypePostRequest() {
    }

    public CuisineTypePostRequest(Integer id, String name, String subtype) {
        this.id = id;
        this.name = name;
        this.subtype = subtype;
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

    @Override
    public String toString() {
        return "CuisineType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtype='" + subtype + '\'' +
                '}';
    }
}
