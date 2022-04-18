package project.ood.healthcalculator.enums;

import project.ood.healthcalculator.entity.FoodNutrients;

public enum FoodNutrientsEnum implements JSONKeyName{
    ID("foodNutrientId"),
    VALUE("value");

    private String field;

    FoodNutrientsEnum(String field) {
        this.field = field;
    }


    @Override
    public String getJSONKeyName() {
        return field;
    }
}
