package project.ood.healthcalculator.enums;

public enum SecondPriorityNutrientEnum {
    VITAMIN_A(1104),
    VITAMIN_C(1162),
    VITAMIN_D(1110),
    VITAMIN_B6(1175),
    VITAMIN_E(1158),
    VITAMIN_K(1185),
    THIAMIN(1165),
    VITAMIN_B12(1178),
    RIBOFLAVIN(1166),
    FOLATE(1177),
    NIACIN(1167),
    CHOLINE(1180),
    PANTOTHENIC_ACID(1170),
    BIOTIN(1176),
    CALCIUM(1087),
    CHLORIDE(0),
    CHROMIUM(0),
    COPPER(1098),
    FLUORIDE(0),
    IODINE(1100),
    IRON(1089),
    MAGNESIUM(1090),
    MANGANESE(1101),
    MOLYBDENUM(1102),
    PHOSPHORUS(1091),
    POTASSIUM(1092),
    SELENIUM(1103),
    SODIUM(1093),
    ZINC(1095);

    private final long nutrientId;

    SecondPriorityNutrientEnum(long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public long getNutrientId() {
        return nutrientId;
    }
}
