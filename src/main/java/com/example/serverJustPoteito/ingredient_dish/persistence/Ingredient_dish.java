package com.example.serverJustPoteito.ingredient_dish.persistence;

import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient_dishKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "INGREDIENT_DISH")
public class Ingredient_dish {

    @EmbeddedId
    Ingredient_dishKey id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", foreignKey = @ForeignKey(name = "fk_ingredient_id2"), nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Ingredient ingredient;

//    @Column(updatable = false, insertable = false)
//    private Integer ingredientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "fk_dish_id2"), nullable = false, insertable = false, updatable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Dish dish;

//    @Column(updatable = false, insertable = false)
//    private Integer dishId;

    @Column
    private String amount;

    public Ingredient_dish() {
    }

    public Ingredient_dish(Integer ingredientId, Integer dishId)  {
        this.id = new Ingredient_dishKey(ingredientId, dishId);
    }

    public Ingredient_dishKey getId() {
        return id;
    }

    public void setId(Ingredient_dishKey id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient_dish{" +
                "id=" + id +
                ", ingredientList=" + ingredient +
                ", dishList=" + dish +
                ", amount='" + amount + '\'' +
                '}';
    }
}
