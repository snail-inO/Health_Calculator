package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Entity
public class FoodNutrients {
    @Id
    private long foodNutrientId;
    private float value;
    @JSONField(serialize = false)
    @ManyToOne
    @JoinColumn(name = "fdc_id")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    public static final class Builder {
        private long foodNutrientId;
        private float value;
        private Food food;
        private Nutrient nutrient;

        private Builder() {}

        public Builder withFoodNutrientId(long foodNutrientId) {
            this.foodNutrientId = foodNutrientId;
            return this;
        }

        public Builder withValue(float value) {
            this.value = value;
            return this;
        }

        public Builder withFood(Food food) {
            this.food = food;
            return this;
        }

        public Builder withNutrient(Nutrient nutrient) {
            this.nutrient = nutrient;
            return this;
        }

        public FoodNutrients build() {
            return new FoodNutrients(this);
        }
    }

    private FoodNutrients(Builder builder) {
        this.foodNutrientId = builder.foodNutrientId;
        this.value = builder.value;
        this.food = builder.food;
        this.nutrient = builder.nutrient;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public FoodNutrients(long foodNutrientId, float value, Food food, Nutrient nutrient) {
        this.foodNutrientId = foodNutrientId;
        this.value = value;
        this.food = food;
        this.nutrient = nutrient;
    }

    public FoodNutrients() {
    }

    public long getFoodNutrientId() {
        return foodNutrientId;
    }

    public void setFoodNutrientId(long foodNutrientId) {
        this.foodNutrientId = foodNutrientId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

}
