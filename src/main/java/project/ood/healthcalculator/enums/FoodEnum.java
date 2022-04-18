package project.ood.healthcalculator.enums;

public enum FoodEnum implements JSONKeyName{
    ID("fdcId"),
    NAME("description"),
    UPC("gtinUpc"),
    SERVING("servingSize"),
    UNIT("servingSizeUnit"),
    WEIGHT("packageWeight"),
    DATA_TYPE("dataType"),
    BRAND("brandName"),
    BRAND_OWNER("brandOwner"),
    INGREDIENTS("ingredients");

    private final String field;

    FoodEnum(String field) {
        this.field = field;
    }

    @Override
    public String getJSONKeyName() {
        return field;
    }
}
