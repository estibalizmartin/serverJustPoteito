package com.example.serverJustPoteito.ingredient.persistence;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ingredients_dish",
            joinColumns =
                    //{
                            @JoinColumn(
                                    name = "dishId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_dishId")
                            )
                    //}
                    /*, @JoinColumn(
                    name = "quantity"
            )}*/
            ,
            inverseJoinColumns = @JoinColumn(
                    name = "ingredientId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_ingredientId")
            )
    )
    private List<Ingredient> ingredients;

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

    public Ingredient(int id, String name, String type, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
