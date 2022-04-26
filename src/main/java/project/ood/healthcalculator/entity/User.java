package project.ood.healthcalculator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;
    @Column(unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    private int userType;
    private int foodCounts;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<UserFoods> userFoods;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<UserMeal> userMeals;

    public User(long uid, String userName, String password, int userType, int foodCounts) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.foodCounts = foodCounts;
    }

    public User() {}

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getFoodCounts() {
        return foodCounts;
    }

    public void setFoodCounts(int foodCounts) {
        this.foodCounts = foodCounts;
    }

}
