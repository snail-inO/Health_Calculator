package project.ood.healthcalculator.enums;

public enum SpecialIdEnum {
    NOT_USED(-1),
    SHARE(1);

    private final long id;

    SpecialIdEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
