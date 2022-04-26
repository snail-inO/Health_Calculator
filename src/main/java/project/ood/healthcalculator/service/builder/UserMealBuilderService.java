package project.ood.healthcalculator.service.builder;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

public interface UserMealBuilderService extends EntityBuilderService {
    UserMealBuilderService setId(long id);
    UserMealBuilderService setName(String name);
    UserMealBuilderService setType(int type);
    UserMealBuilderService setTag(String tag);
    UserMealBuilderService setDescription(String description);
    UserMealBuilderService setUserFoods(List<UserFoods> userFoods);
    UserMealBuilderService setUser(User user);
    UserMeal create();
}
