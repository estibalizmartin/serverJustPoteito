package com.example.serverJustPoteito.dish.persistence;

import com.example.serverJustPoteito.cook.model.Cook;
import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;
import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cook_id", foreignKey = @ForeignKey(name = "fk_cook_id"), nullable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cook cook;

    @Column(name = "cook_id", updatable = false, insertable = false)
    private Integer cookId;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ingredient_dish> ingredient_dishes;

    private String recipe;

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

    public Dish(Integer id, String name, Integer prepTime, String subtype, CuisineType cuisineType, Integer cuisineTypeId, Cook cook, Integer cookId, List<Ingredient_dish> ingredient_dishes) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineType = cuisineType;
        this.cuisineTypeId = cuisineTypeId;
        this.cook = cook;
        this.cookId = cookId;
        this.ingredient_dishes = ingredient_dishes;
    }

    public Dish(Integer id,
                String name,
                Integer prepTime,
                String subtype,
                CuisineType cuisineType,
                Integer cuisineTypeId,
                Cook cook,
                Integer cookId,
                List<Ingredient_dish> ingredient_dishes,
                String recipe) {
        Id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.subtype = subtype;
        this.cuisineType = cuisineType;
        this.cuisineTypeId = cuisineTypeId;
        this.cook = cook;
        this.cookId = cookId;
        this.ingredient_dishes = ingredient_dishes;
        this.recipe = recipe;
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

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Integer getCookId() {
        return cookId;
    }

    public void setCookId(Integer cookId) {
        this.cookId = cookId;
    }

    public List<Ingredient_dish> getIngredient_dishes() {
        return ingredient_dishes;
    }

    public void setIngredient_dishes(List<Ingredient_dish> ingredient_dishes) {
        this.ingredient_dishes = ingredient_dishes;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
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
                ", cook=" + cook +
                ", cookId=" + cookId +
                ", ingredient_dishes=" + ingredient_dishes +
                ", recipe=" + recipe +
                '}';
    }
}
