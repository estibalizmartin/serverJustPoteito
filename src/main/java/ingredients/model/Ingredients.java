package ingredients.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String type;

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ingredients_dish",
            joinColumns = @JoinColumn(
                    name = "ingredientId", referencedColumnName = "id",  foreignKey = @ForeignKey(name = "fk_ingredientId")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "dishId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_dishId")
            )
    )
    private List<Dish> dishes;*/

    public Ingredients() {}

    public Ingredients(String name, String type) {
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
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
