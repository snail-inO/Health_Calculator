package project.ood.healthcalculator.entity;

import javax.persistence.*;

@Entity
public class UserFoods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float value;
    @ManyToOne
    @JoinColumn(name = "uid", foreignKey = @ForeignKey(name = "fk_uid_user"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "fdc_id", foreignKey = @ForeignKey(name = "fk_fdc_id_food"))
    private Food food;

    public UserFoods(float value, User user, Food food) {
        this.value = value;
        this.user = user;
        this.food = food;
    }

    public UserFoods() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Food getFood() {
        return food;
    }

    public void setFoodUser(Food food, User user) {
        this.food = food;
        this.user = user;
    }
}
