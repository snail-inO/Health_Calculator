package project.ood.healthcalculator.entity;

import project.ood.healthcalculator.enums.EntityTypeEnum;

public interface EntityInterface {
    long id();
    long uid();
    EntityTypeEnum entityType();
}
