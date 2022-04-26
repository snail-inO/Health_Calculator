package project.ood.healthcalculator.enums;

public enum MealTypeEnum {
    BREAKFAST(0),
    LUNCH(1),
    DINNER(2),
    SNACK(3),
    OTHER(4),
    UNDEFINE(-1);

    private int num;

    MealTypeEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
