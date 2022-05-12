package project.ood.healthcalculator.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;

@Entity
public class Nutrient {
    @Id
    private long nutrientId;
    private String nutrientName;
    private String unitName;
    private String nutrientNumber;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "nutrient", cascade = {CascadeType.REMOVE})
    private List<FoodNutrients> foodNutrients;
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "nutrient", cascade = CascadeType.REMOVE)
    private List<NutrientCounter> nutrientCounters;

    public static final class Builder {
        private long nutrientId;
        private String nutrientName;
        private String unitName;
        private String nutrientNumber;

        private Builder() {}

        public Builder withNutrientId(long nutrientId) {
            this.nutrientId = nutrientId;
            return this;
        }

        public Builder withNutrientName(String nutrientName) {
            this.nutrientName = nutrientName;
            return this;
        }

        public Builder withUnitName(String unitName) {
            this.unitName = unitName;
            return this;
        }

        public Builder withNutrientNumber(String nutrientNumber) {
            this.nutrientNumber = nutrientNumber;
            return this;
        }

        public Nutrient build() {
            return new Nutrient(this);
        }
    }

    private Nutrient(Builder builder) {
        this.nutrientId = builder.nutrientId;
        this.nutrientName = builder.nutrientName;
        this.unitName = builder.unitName;
        this.nutrientNumber = builder.nutrientNumber;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Nutrient(long nutrientId, String nutrientName, String unitName, String nutrientNumber,
                    List<FoodNutrients> foodNutrients, List<NutrientCounter> nutrientCounters) {
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.unitName = unitName;
        this.nutrientNumber = nutrientNumber;
        this.foodNutrients = foodNutrients;
        this.nutrientCounters = nutrientCounters;
    }

    public Nutrient() {
    }

    public long getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getNutrientNumber() {
        return nutrientNumber;
    }

    public void setNutrientNumber(String nutrientNumber) {
        this.nutrientNumber = nutrientNumber;
    }

    public List<FoodNutrients> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<FoodNutrients> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public List<NutrientCounter> getNutrientCounters() {
        return nutrientCounters;
    }

    public void setNutrientCounters(List<NutrientCounter> nutrientCounters) {
        this.nutrientCounters = nutrientCounters;
    }
}
