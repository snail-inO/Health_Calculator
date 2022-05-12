package project.ood.healthcalculator.enums;

public enum AverageTypeNutritionEnum implements NutritionDataInterface {
    ENERGY(2886, 1008),
    CARBOHYDRATE(395, 1005),
    FIBER(38, 1079),
    PROTEIN(51, 1003),
    FAT(89, 1004),
    LINOLEIC_ACID(17, 1293),
    VITAMIN_A(3000, 1104),
    VITAMIN_C(90, 1162),
    VITAMIN_D(600, 1110),
    VITAMIN_B6(1.3F, 1175),
    VITAMIN_E(15, 1158),
    VITAMIN_K(120, 1185),
    THIAMIN(1.2F, 1165),
    VITAMIN_B12(2.4F, 1178),
    RIBOFLAVIN(2.4F, 1166),
    FOLATE(400, 1177),
    NIACIN(16, 1167),
    CHOLINE(550, 1180),
    PANTOTHENIC_ACID(5, 1170),
    BIOTIN(30, 1176),
    CALCIUM(1000, 1087),
    CHLORIDE(2.3F, 0),
    CHROMIUM(35, 0),
    COPPER(900, 1098),
    FLUORIDE(4, 0),
    IODINE(150, 1100),
    IRON(8, 1089),
    MAGNESIUM(400, 1090),
    MANGANESE(2.3F, 1101),
    MOLYBDENUM(45, 1102),
    PHOSPHORUS(700, 1091),
    POTASSIUM(3400, 1092),
    SELENIUM(55, 1103),
    SODIUM(1500, 1093),
    ZINC(11, 1095);

    private float value;
    private long nutrientId;

    AverageTypeNutritionEnum(float value, int nutrientId) {
        this.value = value;
        this.nutrientId = nutrientId;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public long getNutrientId() {
        return nutrientId;
    }
}
