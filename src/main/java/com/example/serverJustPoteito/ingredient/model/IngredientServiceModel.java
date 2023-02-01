package com.example.serverJustPoteito.ingredient.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientServiceModel {

    private Integer id;
    private String name;
    private String type;
    private String amount;
    private String image;

    public IngredientServiceModel(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public IngredientServiceModel(Integer id, String name, String type, String amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }
    public IngredientServiceModel(Integer id, String name, String type, String amount, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "IngredientServiceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", amount='" + image + '\'' +
                '}';
    }
}
