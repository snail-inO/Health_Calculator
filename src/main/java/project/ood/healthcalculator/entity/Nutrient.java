package project.ood.healthcalculator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Nutrient {
    @Id
    private long nutrientId;
    private String nutrientName;
    private String unitName;
    private String nutrientNumber;
    @OneToMany(mappedBy = "nutrient", cascade = {CascadeType.REMOVE})
    private List<FoodNutrients> foodNutrients;

    public Nutrient() {}

    public Nutrient(long nutrientId, String nutrientName, String unitName, String nutrientNumber) {
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.unitName = unitName;
        this.nutrientNumber = nutrientNumber;
    }


    public void setFoodNutrients(List<FoodNutrients> foodNutrients) {
        this.foodNutrients = foodNutrients;
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
}
