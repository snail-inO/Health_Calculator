package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.UserFoods;

import java.util.List;

public interface UserFoodService {
    UserFoods addFood(long id, float value);
    UserFoods removeFood(long id);
    UserFoods updateFood(long id, float value);
    List<UserFoods> showFoods();
}
