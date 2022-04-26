package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

public class UserFoodsBuilderServiceImpl implements UserFoodsBuilderService {
    private UserFoods userFoods;

    public UserFoodsBuilderServiceImpl() {
        userFoods = new UserFoods();
    }

    @Override
    public UserFoodsBuilderService setId(long fdcId) {
        userFoods.setId(fdcId);
        return this;
    }

    @Override
    public UserFoodsBuilderService setValue(float value) {
        userFoods.setValue(value);
        return this;
    }

    @Override
    public UserFoodsBuilderService setFoodUser(Food food, User user) {
        userFoods.setFoodUser(food, user);
        return this;
    }

    @Override
    public UserFoodsBuilderService setUserMeals(List<UserMeal> userMeals) {
        userFoods.setUserMeals(userMeals);
        return this;
    }

    @Override
    public UserFoodsBuilderService setUserFoods(UserFoods userFoods) {
        this.userFoods = userFoods;
        return this;
    }

    @Override
    public UserFoods build() {
        return userFoods;
    }

}
