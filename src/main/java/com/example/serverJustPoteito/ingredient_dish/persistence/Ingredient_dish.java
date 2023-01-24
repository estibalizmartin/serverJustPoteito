package com.example.serverJustPoteito.ingredient_dish.persistence;

import com.example.serverJustPoteito.dish.persistence.Dish;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient_dishKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
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

    public Ingredient_dish(Ingredient ingredient, Integer ingredientId, Dish dish, Integer dishId, String amount) {
        this.ingredient = ingredient;

        this.dish = dish;

        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

//    public Integer getIngredientId() {
//        return ingredientId;
//    }
//
//    public void setIngredientId(Integer ingredientId) {
//        this.ingredientId = ingredientId;
//    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

//    public Integer getDishId() {
//        return dishId;
//    }
//
//    public void setDishId(Integer dishId) {
//        this.dishId = dishId;
//    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient_dish{" +
                "ingredient=" + ingredient +

                ", dish=" + dish +

                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient_dish that = (Ingredient_dish) o;
        return id == that.id && Objects.equals(ingredient, that.ingredient) && Objects.equals(dish, that.dish) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, dish, amount);
    }
}
