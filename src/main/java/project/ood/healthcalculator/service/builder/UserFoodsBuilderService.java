package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

public interface UserFoodsBuilderService extends EntityBuilderService {
    UserFoodsBuilderService setId(long fdcId);
    UserFoodsBuilderService setValue(float value);
    UserFoodsBuilderService setFoodUser(Food food, User user);
    UserFoodsBuilderService setUserMeals(List<UserMeal> userMeals);
    UserFoodsBuilderService setUserFoods(UserFoods userFoods);
    UserFoods build();
}
