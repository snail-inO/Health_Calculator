package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;
import project.ood.healthcalculator.enums.EntityTypeEnum;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Entity
public class User implements EntityInterface{
    private static final EntityTypeEnum entityType = EntityTypeEnum.USER;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    @Column(unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    private int userType;
    private int foodCount;
    private int mealCount;
    private int recipeCount;
    private int age;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFoods> userFoods;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMeal> userMeals;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserRecipe> userRecipes;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<NutrientCounter> nutrientCounters;

    public static final class Builder {
        private long uid;
        private String userName;
        private String password;
        private int userType;
        private int foodCount;
        private int mealCount;
        private int recipeCount;
        private int age;

        private Builder() {}

        public Builder withSession(HttpSession session) {
            uid = (long) session.getAttribute("userId");
            return this;
        }

        public Builder withUid(long uid) {
            this.uid = uid;
            return this;
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withUserType(int userType) {
            this.userType = userType;
            return this;
        }

        public Builder withFoodCounts(int foodCount) {
            this.foodCount = foodCount;
            return this;
        }

        public Builder withMealCount(int mealCount) {
            this.mealCount = mealCount;
            return this;
        }

        public Builder withRecipeCount(int recipeCount) {
            this.recipeCount = recipeCount;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        this.uid = builder.uid;
        this.userName = builder.userName;
        this.password = builder.password;
        this.userType = builder.userType;
        this.foodCount = builder.foodCount;
        this.mealCount = builder.mealCount;
        this.recipeCount = builder.recipeCount;
        this.age = builder.age;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int increaseCount(EntityTypeEnum e, int value) {
        switch (e) {
            case FOOD:
                return foodCount += value;
            case MEAL:
                return mealCount += value;
            case RECIPE:
                return recipeCount += value;
            default:
                return -1;
        }
    }

    @Override
    public EntityTypeEnum entityType() {
        return entityType;
    }

    @Override
    public long id() {
        return uid;
    }

    @Override
    public long uid() {
        return uid;
    }

    public User(long uid, String userName, String password, int userType, int foodCount, int mealCount, int recipeCount,
                int age, List<UserFoods> userFoods, List<UserMeal> userMeals, List<UserRecipe> userRecipes,
                List<NutrientCounter> nutrientCounters) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.foodCount = foodCount;
        this.mealCount = mealCount;
        this.recipeCount = recipeCount;
        this.age = age;
        this.userFoods = userFoods;
        this.userMeals = userMeals;
        this.userRecipes = userRecipes;
        this.nutrientCounters = nutrientCounters;
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

    public int getFoodCount() {
        return foodCount;
    }

    public User setFoodCount(int foodCount) {
        this.foodCount = foodCount;
        return this;
    }

    public int getMealCount() {
        return mealCount;
    }

    public User setMealCount(int mealCount) {
        this.mealCount = mealCount;
        return this;
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public User setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
        return this;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<UserFoods> getUserFoods() {
        return userFoods;
    }

    public void setUserFoods(List<UserFoods> userFoods) {
        this.userFoods = userFoods;
    }

    public List<UserMeal> getUserMeals() {
        return userMeals;
    }

    public void setUserMeals(List<UserMeal> userMeals) {
        this.userMeals = userMeals;
    }

    public List<UserRecipe> getUserRecipes() {
        return userRecipes;
    }

    public void setUserRecipes(List<UserRecipe> userRecipes) {
        this.userRecipes = userRecipes;
    }

    public List<NutrientCounter> getNutrientCounters() {
        return nutrientCounters;
    }

    public void setNutrientCounters(List<NutrientCounter> nutrientCounters) {
        this.nutrientCounters = nutrientCounters;
    }
}
