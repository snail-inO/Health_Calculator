package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.Food;

public class FoodBuilderServiceImpl implements FoodBuilderService {
    Food food;

    public FoodBuilderServiceImpl() {
        food = new Food();
    }
    @Override
    public FoodBuilderService setId(long fdcId) {
        food.setFdcId(fdcId);
        return this;
    }

    @Override
    public Food build() {
        return food;
    }
}
