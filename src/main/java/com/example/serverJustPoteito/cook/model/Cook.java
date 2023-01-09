package com.example.serverJustPoteito.cook.model;

import com.example.serverJustPoteito.dish.model.Dish;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cooks")
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 70)
    private String name;
    @Column(length = 140)
    private String last_names;
    @Column
    private String nationality;
    @Temporal(TemporalType.DATE)
    @Column(name = "born_date")
    private Date bornDate;
    @Column
    private Integer michelin_stars;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cooks_dishes",
            joinColumns = @JoinColumn(
                    name = "cook_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_cook_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "dish_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_dish_id")
            )
    )
    private List<Dish> dishes;

    public Cook() {
    }

    public Cook(Integer id, String name, String last_names, String nationality, Date bornDate, Integer michelin_stars, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.last_names = last_names;
        this.nationality = nationality;
        this.bornDate = bornDate;
        this.michelin_stars = michelin_stars;
        this.dishes = dishes;
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

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Integer getMichelin_stars() {
        return michelin_stars;
    }

    public void setMichelin_stars(Integer michelin_stars) {
        this.michelin_stars = michelin_stars;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sur_names='" + last_names + '\'' +
                ", nationality='" + nationality + '\'' +
                ", born_date=" + bornDate +
                ", michelin_stars=" + michelin_stars +
                ", dishes=" + dishes +
                '}';
    }
}
