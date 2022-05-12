package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Entity
public class NutrientCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nutrientCounterId;
    private float value;
    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "user_foods_id")
    private UserFoods userFoods;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "user_meal_id")
    private UserMeal userMeal;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "user_recipe_id")
    private UserRecipe userRecipe;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    public static final class Builder {
        private long nutrientCounterId;
        private float value;
        private Nutrient nutrient;
        private UserFoods userFoods;
        private UserMeal userMeal;
        private UserRecipe userRecipe;
        private User user;

        private Builder() {}

        public Builder withNutrientCounter(NutrientCounter nutrientCounter) {
            this.nutrientCounterId = nutrientCounter.nutrientCounterId;
            this.value = nutrientCounter.value;
            this.nutrient = nutrientCounter.nutrient;
            this.userFoods = nutrientCounter.userFoods;
            this.userMeal = nutrientCounter.userMeal;
            this.userRecipe = nutrientCounter.userRecipe;
            this.user = nutrientCounter.user;

            return this;
        }
        public Builder withNutrientCounterId(long nutrientCounterId) {
            this.nutrientCounterId = nutrientCounterId;
            return this;
        }

        public Builder withValue(float value) {
            this.value = value;
            return this;
        }

        public Builder withNutrient(Nutrient nutrient) {
            this.nutrient = nutrient;
            return this;
        }

        public Builder withUserFoods(UserFoods userFoods) {
            this.userFoods = userFoods;
            return this;
        }

        public Builder withUserMeal(UserMeal userMeal) {
            this.userMeal = userMeal;
            return this;
        }

        public Builder withUserRecipe(UserRecipe userRecipe) {
            this.userRecipe = userRecipe;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public NutrientCounter build() {
            return new NutrientCounter(this);
        }
    }

    private NutrientCounter(Builder builder) {
        this.nutrientCounterId = builder.nutrientCounterId;
        this.value = builder.value;
        this.nutrient = builder.nutrient;
        this.userFoods = builder.userFoods;
        this.userMeal = builder.userMeal;
        this.userRecipe = builder.userRecipe;
        this.user = builder.user;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public NutrientCounter(long nutrientCounterId, float value, Nutrient nutrient, UserFoods userFoods, UserMeal userMeal,
                           UserRecipe userRecipe, User user) {
        this.nutrientCounterId = nutrientCounterId;
        this.value = value;
        this.nutrient = nutrient;
        this.userFoods = userFoods;
        this.userMeal = userMeal;
        this.userRecipe = userRecipe;
        this.user = user;
    }

    public NutrientCounter() {
    }

    public long getNutrientCounterId() {
        return nutrientCounterId;
    }

    public void setNutrientCounterId(long nutrientCounterId) {
        this.nutrientCounterId = nutrientCounterId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public UserFoods getUserFoods() {
        return userFoods;
    }

    public void setUserFoods(UserFoods userFoods) {
        this.userFoods = userFoods;
    }

    public UserMeal getUserMeal() {
        return userMeal;
    }

    public void setUserMeal(UserMeal userMeal) {
        this.userMeal = userMeal;
    }

    public UserRecipe getUserRecipe() {
        return userRecipe;
    }

    public void setUserRecipe(UserRecipe userRecipe) {
        this.userRecipe = userRecipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
