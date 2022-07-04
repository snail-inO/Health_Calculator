package project.ood.healthcalculator.enums;

public enum FirstPriorityNutrientEnum {
    ENERGY(1008),
    CARBOHYDRATE(1005),
    FIBER(1079),
    PROTEIN(1003),
    FAT(1004),
    LINOLEIC_ACID(1293);

    private final long nutrientId;


    FirstPriorityNutrientEnum(long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public long getNutrientId() {
        return nutrientId;
    }
}
