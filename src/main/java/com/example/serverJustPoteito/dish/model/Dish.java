package com.example.serverJustPoteito.dish.model;

import com.example.serverJustPoteito.cuisineType.model.CuisineType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    /*@Column
    @Enumerated(EnumType.STRING)
    private Allergens allergen;*/
    @Column(length = 70)
    private String subtype;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_type_id", foreignKey = @ForeignKey(name = "fk_cuisine_type_id"), nullable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CuisineType cuisineType;

    @Column(name = "cuisine_type_id", updatable = false, insertable = false)
    private Integer cuisineTypeId;

    public Dish() {
    }

    public Dish(Integer id, String name, Integer prepTime, String subtype, CuisineType cuisineType) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineType = cuisineType;
    }

    public Dish(Integer id, String name, Integer prepTime, String subtype, CuisineType cuisineType, Integer cuisineTypeId) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineType = cuisineType;
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

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public Integer getCuisineTypeId() {
        return cuisineTypeId;
    }

    public void setCuisineTypeId(Integer cuisineTypeId) {
        this.cuisineTypeId = cuisineTypeId;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                ", subtype='" + subtype + '\'' +
                ", cuisineType=" + cuisineType +
                ", cuisineTypeId=" + cuisineTypeId +
                '}';
    }
}
