package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;
import project.ood.healthcalculator.enums.EntityTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserMeal implements EntityInterface {
    private static final EntityTypeEnum entityType = EntityTypeEnum.MEAL;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userMealId;
    private String name;
    private int type;
    private String tag;
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean shared;
    @ManyToMany
    @JoinTable(
            name = "user_foods_meal",
            joinColumns = @JoinColumn(name = "user_meal_id"),
            inverseJoinColumns = @JoinColumn(name = "user_foods_id")
    )
    private List<UserFoods> userFoods;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "uid", foreignKey = @ForeignKey(name = "fk_uid_user"))
    private User user;
    @JSONField(serialize = false)
    @ManyToMany(mappedBy = "userMeals", cascade = CascadeType.REMOVE)
    private List<UserRecipe> userRecipes;
    @OneToMany(mappedBy = "userMeal", cascade = CascadeType.REMOVE)
    private List<NutrientCounter> nutrientCounters;

    public static final class Builder {
        private long userMealId;
        private String name;
        private int type;
        private String tag;
        private String description;
        private boolean shared;
        private List<UserFoods> userFoods;
        private User user;
        private List<NutrientCounter> nutrientCounters;

        private Builder() {}

        public Builder withUserMeal(UserMeal userMeal) {
            this.userMealId = userMeal.userMealId;
            this.name = userMeal.name;
            this.type = userMeal.type;
            this.tag = userMeal.tag;
            this.description = userMeal.description;
            this.shared = userMeal.shared;
            this.userFoods = userMeal.userFoods;
            this.user = userMeal.user;
            this.nutrientCounters = userMeal.nutrientCounters;

            return this;
        }

        public Builder withUserMealId(long userMealId) {
            this.userMealId = userMealId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withShared(boolean shared) {
            this.shared = shared;
            return this;
        }

        public Builder withUserFoods(List<UserFoods> userFoods) {
            this.userFoods = userFoods;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserMeal build() {
            return new UserMeal(this);
        }
    }

    private UserMeal(Builder builder) {
        this.userMealId = builder.userMealId;
        this.name = builder.name;
        this.type = builder.type;
        this.tag = builder.tag;
        this.description = builder.description;
        this.shared = builder.shared;
        this.userFoods = builder.userFoods;
        this.user = builder.user;
        this.nutrientCounters = builder.nutrientCounters;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public long id() {
        return userMealId;
    }

    @Override
    public long uid() {
        return user.getUid();
    }

    @Override
    public EntityTypeEnum entityType() {
        return entityType;
    }

    public UserMeal(long userMealId, String name, int type, String tag, String description, List<UserFoods> userFoods,
                    User user, List<UserRecipe> userRecipes, List<NutrientCounter> nutrientCounters) {
        this.userMealId = userMealId;
        this.name = name;
        this.type = type;
        this.tag = tag;
        this.description = description;
        this.userFoods = userFoods;
        this.user = user;
        this.userRecipes = userRecipes;
        this.nutrientCounters = nutrientCounters;
    }

    public UserMeal() {
    }

    public long getUserMealId() {
        return userMealId;
    }

    public void setUserMealId(long userMealId) {
        this.userMealId = userMealId;
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

    public boolean isShared() {
        return shared;
    }

    public UserMeal setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<UserFoods> getUserFoods() {
        return userFoods;
    }

    public void setUserFoods(List<UserFoods> userFoods) {
        this.userFoods = userFoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
