package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

public class UserMealBuilderServiceImpl implements UserMealBuilderService {
    private UserMeal userMeal;

    public UserMealBuilderServiceImpl() {
        userMeal = new UserMeal();
    }

    @Override
    public UserMealBuilderService setId(long id) {
        userMeal.setId(id);
        return this;
    }

    @Override
    public UserMealBuilderService setName(String name) {
        userMeal.setName(name);
        return this;
    }

    @Override
    public UserMealBuilderService setType(int type) {
        userMeal.setType(type);
        return this;
    }

    @Override
    public UserMealBuilderService setTag(String tag) {
        userMeal.setTag(tag);
        return this;
    }

    @Override
    public UserMealBuilderService setDescription(String description) {
        userMeal.setDescription(description);
        return this;
    }

    @Override
    public UserMealBuilderService setUserFoods(List<UserFoods> userFoods) {
        userMeal.setUserFoods(userFoods);
        return this;
    }

    @Override
    public UserMealBuilderService setUser(User user) {
        userMeal.setUser(user);
        return this;
    }

    @Override
    public UserMeal create() {
        return userMeal;
    }
}
