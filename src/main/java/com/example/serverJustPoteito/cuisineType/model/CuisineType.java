package com.example.serverJustPoteito.cuisineType.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CUISINETYPE")
public class CuisineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 70)
    private String name;
    @Column(length = 120)
    private String subtype;

    public CuisineType() {
    }
    public CuisineType(String name, String subtype) {
        this.name = name;
        this.subtype = subtype;
    }

    public CuisineType(Integer id, String name, String subtype) {
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
