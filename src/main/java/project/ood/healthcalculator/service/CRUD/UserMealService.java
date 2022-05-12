package project.ood.healthcalculator.service.CRUD;

import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;

import java.util.List;

public interface UserMealService{
    List<UserMeal> retrieveAll(User user);
    List<UserMeal> retrieveAll(boolean shared);
}
