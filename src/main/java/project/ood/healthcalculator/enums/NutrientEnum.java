package project.ood.healthcalculator.enums;

public enum NutrientEnum implements JSONKeyName{
    ID("nutrientId"),
    NAME("nutrientName"),
    UNIT("unitName"),
    NUMBER("nutrientNumber");

    private String field;

    NutrientEnum(String field) {
        this.field = field;
    }

    @Override
    public String getJSONKeyName() {
        return field;
    }
}
