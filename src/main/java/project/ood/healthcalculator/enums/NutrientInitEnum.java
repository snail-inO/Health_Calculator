package project.ood.healthcalculator.enums;

public enum NutrientInitEnum {
    CHICKEN("chicken", 1),
    TOMATO("tomato", 1),
    BREAD("bread", 1),
    RICE("rice", 1),
    APPLE("apple", 1),
    LETTUCE("lettuce", 1),
    WHEAT_GERM_OIL("wheat germ oil", 1),
    EGG("egg", 5),
    WHOLE_GRAINS("whole grains", 3);

    private final String name;
    private final int pageNum;

    NutrientInitEnum(String name, int pageNum) {
        this.name = name;
        this.pageNum = pageNum;
    }

    public String getName() {
        return name;
    }

    public int getPageNum() {
        return pageNum;
    }
}
