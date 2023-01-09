package com.example.serverJustPoteito.cook.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CookPostRequest {
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;
    private String last_names;
    private String nationality;
    @Temporal(TemporalType.DATE)
    private Date born_date;
    private Integer michelin_stars;

    public CookPostRequest() {
    }

    public CookPostRequest(String name, String last_names, String nationality, Date born_date, Integer michelin_stars) {
        this.name = name;
        this.last_names = last_names;
        this.nationality = nationality;
        this.born_date = born_date;
        this.michelin_stars = michelin_stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_names() {
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public Integer getMichelin_stars() {
        return michelin_stars;
    }

    public void setMichelin_stars(Integer michelin_stars) {
        this.michelin_stars = michelin_stars;
    }

    @Override
    public String toString() {
        return "CookPostRequest{" +
                "name='" + name + '\'' +
                ", last_names='" + last_names + '\'' +
                ", nationality='" + nationality + '\'' +
                ", born_date=" + born_date +
                ", michelin_stars=" + michelin_stars +
                '}';
    }
}
