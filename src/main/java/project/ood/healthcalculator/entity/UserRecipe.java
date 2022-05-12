package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;
import project.ood.healthcalculator.enums.EntityTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserRecipe implements EntityInterface {
    private static final EntityTypeEnum entityType = EntityTypeEnum.RECIPE;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRecipeId;
    private String name;
    private int sequenceId;
    private boolean shared;
    @ManyToMany
    @JoinTable(
            name = "user_recipe_meal",
            joinColumns = @JoinColumn(name = "user_recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_meal_id")
    )
    private List<UserMeal> userMeals;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @OneToMany(mappedBy = "userRecipe", cascade = CascadeType.REMOVE)
    private List<NutrientCounter> nutrientCounters;

    public static final class Builder {
        private long userRecipeId;
        private String name;
        private int sequenceId;
        private boolean shared;
        private List<UserMeal> userMeals;
        private User user;

        private Builder() {}

        public Builder withUserRecipe(UserRecipe userRecipe){
            this.userRecipeId = userRecipe.userRecipeId;
            this.name = userRecipe.name;
            this.sequenceId = userRecipe.sequenceId;
            this.shared = userRecipe.shared;
            this.userMeals = userRecipe.userMeals;
            this.user = userRecipe.user;

            return this;
        }

        public Builder withUserRecipeId(long userRecipeId) {
            this.userRecipeId = userRecipeId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSequenceId(int sequenceId) {
            this.sequenceId = sequenceId;
            return this;
        }

        public Builder withShared(boolean shared) {
            this.shared = shared;
            return this;
        }

        public Builder withUserMeals(List<UserMeal> userMeals) {
            this.userMeals = userMeals;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserRecipe build() {
            return new UserRecipe(this);
        }
    }

    private UserRecipe(Builder builder) {
        this.userRecipeId = builder.userRecipeId;
        this.name = builder.name;
        this.sequenceId = builder.sequenceId;
        this.shared = builder.shared;
        this.userMeals = builder.userMeals;
        this.user = builder.user;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public long id() {
        return userRecipeId;
    }

    @Override
    public long uid() {
        return user.getUid();
    }

    @Override
    public EntityTypeEnum entityType() {
        return entityType;
    }

    public UserRecipe(long userRecipeId, String name, int sequenceId, List<UserMeal> userMeals, User user,
                      List<NutrientCounter> nutrientCounters) {
        this.userRecipeId = userRecipeId;
        this.name = name;
        this.sequenceId = sequenceId;
        this.userMeals = userMeals;
        this.user = user;
        this.nutrientCounters = nutrientCounters;
    }

    public UserRecipe() {
    }

    public long getUserRecipeId() {
        return userRecipeId;
    }

    public void setUserRecipeId(long userRecipeId) {
        this.userRecipeId = userRecipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public boolean isShared() {
        return shared;
    }

    public UserRecipe setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<UserMeal> getUserMeals() {
        return userMeals;
    }

    public void setUserMeals(List<UserMeal> userMeals) {
        this.userMeals = userMeals;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NutrientCounter> getNutrientCounters() {
        return nutrientCounters;
    }

    public void setNutrientCounters(List<NutrientCounter> nutrientCounters) {
        this.nutrientCounters = nutrientCounters;
    }
}
