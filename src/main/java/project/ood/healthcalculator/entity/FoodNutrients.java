package project.ood.healthcalculator.entity;

import javax.persistence.*;

@Entity
public class FoodNutrients {
    @Id
    private long foodNutrientId;
    private float value;
    @ManyToOne
    @JoinColumn(name = "fdc_id", foreignKey = @ForeignKey(name = "fk_fdc_id_food"))
    private Food food;
    @ManyToOne
    @JoinColumn(name = "nutrient_id", foreignKey = @ForeignKey(name = "fk_nutrient_id_nutrient"))
    private Nutrient nutrient;

    public FoodNutrients() {}

    public FoodNutrients(long foodNutrientId, float value, Food food, Nutrient nutrient) {
        this.foodNutrientId = foodNutrientId;
        this.value = value;
        this.food = food;
        this.nutrient = nutrient;
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

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public void setFoodNutrient(Food food, Nutrient nutrient) {
        this.food = food;
        this.nutrient = nutrient;
    }
}
