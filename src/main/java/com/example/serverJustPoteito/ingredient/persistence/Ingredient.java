package com.example.serverJustPoteito.ingredient.persistence;

import com.example.serverJustPoteito.ingredient_dish.persistence.Ingredient_dish;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String type;

    @Column(length = 500)
    private String image;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Ingredient_dish> ingredient_dishes;

    public Ingredient() {}

    public Ingredient(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Ingredient(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public Ingredient(int id, String name, String type, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient_dish> getIngredient_dishes() {
        return ingredient_dishes;
    }

    public void setIngredient_dishes(List<Ingredient_dish> ingredient_dishes) {
        this.ingredient_dishes = ingredient_dishes;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
