package project.ood.healthcalculator.enums;

import com.alibaba.fastjson.JSON;

public enum FoodQueryEnum implements JSONKeyName{
    FOODS("foods"),
    NUTRIENTS("foodNutrients");

    private String field;

    FoodQueryEnum(String field) {
        this.field = field;
    }

    @Override
    public String getJSONKeyName() {
        return field;
    }
}
