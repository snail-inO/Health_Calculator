package project.ood.healthcalculator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int type;
    private String tag;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_foods_meal",
            joinColumns = @JoinColumn(name = "user_meal_id", referencedColumnName = "id", foreignKey =
                                    @ForeignKey(name = "fk_user_foods_meal_meal")),
            inverseJoinColumns = @JoinColumn(name = "user_foods_id", referencedColumnName = "id", foreignKey =
                                    @ForeignKey(name = "fk_user_foods_meal_foods"))
    )
    private List<UserFoods> userFoods;
    @ManyToOne
    @JoinColumn(name = "uid", foreignKey = @ForeignKey(name = "fk_uid_user"))
    private User user;

    public UserMeal(long id, String name, int type, String tag, String description, List<UserFoods> userFoods, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tag = tag;
        this.description = description;
        this.userFoods = userFoods;
        this.user = user;
    }

    public UserMeal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserFoods> getUserFoods() {
        return userFoods;
    }

    public void setUserFoods(List<UserFoods> userFoods) {
        this.userFoods = userFoods;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
