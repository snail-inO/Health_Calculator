package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.Food;

public interface FoodBuilderService extends EntityBuilderService {
    FoodBuilderService setId(long fdcId);
    Food build();
}
