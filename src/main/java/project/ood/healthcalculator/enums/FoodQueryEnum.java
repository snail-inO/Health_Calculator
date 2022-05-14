package project.ood.healthcalculator.enums;

public enum FoodQueryEnum implements JSONKeyName{
    FOODS("foods"),
    NUTRIENTS("foodNutrients");

    private final String field;

    FoodQueryEnum(String field) {
        this.field = field;
    }

    @Override
    public String getJSONKeyName() {
        return field;
    }
}
