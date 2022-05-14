package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;
import project.ood.healthcalculator.enums.EntityTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserFoods implements EntityInterface {
    private static final EntityTypeEnum entityType = EntityTypeEnum.FOOD;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userFoodsId;
    private float value;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "fdc_id")
    private Food food;
    @JSONField(serialize = false)
    @ManyToMany(mappedBy = "userFoods", cascade = CascadeType.REMOVE)
    private List<UserMeal> userMeals;
    @OneToMany(mappedBy = "userFoods", cascade = CascadeType.REMOVE)
    private List<NutrientCounter> nutrientCounters;

    public static final class Builder {
        private long userFoodsId;
        @Column(nullable = false)
        private float value;
        private User user;
        private Food food;
        private List<NutrientCounter> nutrientCounters;

        private Builder() {}

        public Builder withUserFoods(UserFoods userFoods) {
            this.userFoodsId = userFoods.userFoodsId;
            this.value = userFoods.value;
            this.user = userFoods.user;
            this.food = userFoods.food;
            this.nutrientCounters = userFoods.nutrientCounters;

            return this;
        }
        public Builder withUserFoodsId(long userFoodsId) {
            this.userFoodsId = userFoodsId;
            return this;
        }

        public Builder withValue(float value) {
            this.value = value;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withFood(Food food) {
            this.food = food;
            return this;
        }

        public UserFoods build() {
            return new UserFoods(this);
        }
    }

    private UserFoods(Builder builder) {
        this.userFoodsId = builder.userFoodsId;
        this.value = builder.value;
        this.user = builder.user;
        this.food = builder.food;
        this.nutrientCounters = builder.nutrientCounters;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public long id() {
        return userFoodsId;
    }

    @Override
    public long uid() {
        return user.getUid();
    }

    @Override
    public EntityTypeEnum entityType() {
        return entityType;
    }

    public UserFoods(long userFoodsId, float value, User user, Food food, List<UserMeal> userMeals,
                     List<NutrientCounter> nutrientCounters) {
        this.userFoodsId = userFoodsId;
        this.value = value;
        this.user = user;
        this.food = food;
        this.userMeals = userMeals;
        this.nutrientCounters = nutrientCounters;
    }

    public UserFoods() {}

    public long getUserFoodsId() {
        return userFoodsId;
    }

    public void setUserFoodsId(long userFoodsId) {
        this.userFoodsId = userFoodsId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public List<UserMeal> getUserMeals() {
        return userMeals;
    }

    public void setUserMeals(List<UserMeal> userMeals) {
        this.userMeals = userMeals;
    }

    public List<NutrientCounter> getNutrientCounters() {
        return nutrientCounters;
    }

    public void setNutrientCounters(List<NutrientCounter> nutrientCounters) {
        this.nutrientCounters = nutrientCounters;
    }
}
