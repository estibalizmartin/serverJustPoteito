package com.example.serverJustPoteito.ingredients.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public class IngredientsPostRequest {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String type;

    public IngredientsPostRequest() {}

    public IngredientsPostRequest(String name, String type) {
        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return "IngredientsPostRequest{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
